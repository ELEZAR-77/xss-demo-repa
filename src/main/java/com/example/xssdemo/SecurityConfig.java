package com.example.xssdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers
                        .contentSecurityPolicy(csp ->
                                csp.policyDirectives("script-src 'self'")
                        )
                );

        return http.build();
    }
}