package com.bank.kyc.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bank.kyc.security.JwtFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final JwtFilter jwtFilter;

    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http)
            throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/kyc")
                .hasRole("CUSTOMER")

                .requestMatchers("/kyc/me")
                .hasRole("CUSTOMER")

                .requestMatchers("/kyc/pending")
                .hasAnyRole("EMPLOYEE", "ADMIN")

                .requestMatchers("/kyc/*/approve")
                .hasAnyRole("EMPLOYEE", "ADMIN")

                .requestMatchers("/kyc/*/reject")
                .hasAnyRole("EMPLOYEE", "ADMIN")

                .anyRequest()
                .authenticated()
            )

            .addFilterBefore(
                    jwtFilter,
                    UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
