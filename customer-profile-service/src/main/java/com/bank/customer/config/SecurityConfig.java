package com.bank.customer.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bank.customer.security.JwtFilter;

@Configuration
public class SecurityConfig {

	@Autowired
	private JwtFilter jwtFilter;

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http)
	        throws Exception {

	    http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth

	        		.requestMatchers("/profile/me")
	        		.hasRole("CUSTOMER")

	        		.requestMatchers(HttpMethod.POST, "/profile")
	        		.hasRole("CUSTOMER")

	        		.requestMatchers("/profile/*")
	        		.hasAnyRole("EMPLOYEE","ADMIN")

	        		.requestMatchers(HttpMethod.GET, "/profile/all")
	        		.hasAnyRole("EMPLOYEE","ADMIN")
	            .anyRequest()
	            .authenticated()
	            )

	        .addFilterBefore(jwtFilter,
	                UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}
}
