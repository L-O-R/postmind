package com.postmind.backend.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import java.util.List;

//allow => public routes
//protect => private routes
// session based token disable
// password encryt
// authentication
// Cors => cross origin

// localhost:5173 => frontend
// localhost:8000 => backend

@Configuration
//settings file for spring security
public class SecurityConfig {
// password encode bean for hashing the password
    @Bean
    public PasswordEncoder passwordEncoder() {
        // brute force
        // salted hashing
        // SCrypt
        return  new BCryptPasswordEncoder();
    }

//    Authentication
    @Bean
//    spring security => security guard => login verification machine
//    user crediantls=> check / verify => valid / invalid
    public AuthenticationManager  authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

//    spring main configuration
    @Bean
//    defines entire security behaviour
    // rules for incoming request
    public SecurityFilterChain springSecurityFilterChain(HttpSecurity http) throws Exception {
//        HttpSecurity => authentication  + authorization + CORS + CSRF + sessions + routes
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(request -> {
                            CorsConfiguration corsConfiguration = new CorsConfiguration();
                            corsConfiguration.setAllowedOrigins(
                                    List.of("http://localhost:5173")
                            );
//                             cookies we want
                            corsConfiguration.setAllowCredentials(true);

                            corsConfiguration.setAllowedMethods(
                                    List.of("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD", "OPTIONS")
                            );

                            corsConfiguration.setAllowedHeaders(List.of("*"));

                            return corsConfiguration;
                        }))

                .sessionManagement(
                        session -> session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/auth/**").permitAll()
                                        .anyRequest().authenticated()
                );

        return http.build();
    }

}
