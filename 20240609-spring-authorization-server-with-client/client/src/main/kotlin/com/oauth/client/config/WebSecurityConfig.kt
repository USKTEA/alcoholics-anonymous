package com.oauth.client.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler

@Configuration
@EnableWebSecurity
class WebSecurityConfig {

    @Bean
    fun securityFilterChain(
        http: HttpSecurity,
        clientRegistrationRepository: ClientRegistrationRepository,
        authorizedClientRepository: OAuth2AuthorizedClientRepository
    ): SecurityFilterChain {
        http.authorizeHttpRequests { auth ->
            auth.anyRequest().authenticated()
        }
//            .oauth2Login { login ->
//            login.loginPage("/oauth2/authorization/articles-client-oidc")
//        }.
            .oauth2Client(Customizer.withDefaults())

        return http.build()
    }

    private fun handler(): SimpleUrlAuthenticationSuccessHandler {
        val logoutSuccessHandler = SimpleUrlAuthenticationSuccessHandler()
        logoutSuccessHandler.setUseReferer(true)
        return logoutSuccessHandler
    }
}
