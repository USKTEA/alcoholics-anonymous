package com.security.template.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authorization.AuthorizationManager
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.savedrequest.HttpSessionRequestCache

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
class WebSecurityConfig {

    @Bean
    fun webSecurityFilterChain(http: HttpSecurity): SecurityFilterChain {
        val requestCache = HttpSessionRequestCache()
        requestCache.setMatchingRequestParameterName("cached=true")

        http.authorizeHttpRequests { auth ->
            auth.requestMatchers("/not-welcome").permitAll()
            auth.anyRequest().authenticated()
        }.formLogin { formLogin ->
            formLogin.successHandler { request, response, authentication ->
                val cachedRequest = requestCache.getRequest(request, response)
                response.sendRedirect(cachedRequest.redirectUrl)
            }
                .failureHandler { request, response, auth ->
                    response.sendRedirect("/not-welcome")
                }
                .permitAll()
        }.rememberMe { rememberMe ->
            rememberMe.tokenValiditySeconds(3600)
            rememberMe.userDetailsService(userDetailService())
        }.anonymous { anonymous ->
            anonymous.principal("guest")
            anonymous.authorities("ROLE_GUEST")
        }.logout { logout ->
            logout.addLogoutHandler { request, response, authentication ->
                response.sendRedirect("/bye")
            }
        }.requestCache { cache ->
            cache.requestCache(requestCache)
        }

        return http.build()
    }

    @Bean
    fun userDetailService(): UserDetailsService {
        val user = User.builder()
            .username("user")
            .password("{noop}1111")
            .roles("USER")
            .build()

        return InMemoryUserDetailsManager(user)
    }
}
