package com.usktea.security.config

import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity

@EnableReactiveMethodSecurity(useAuthorizationManager = true)
class MethodSecurityConfig {

}