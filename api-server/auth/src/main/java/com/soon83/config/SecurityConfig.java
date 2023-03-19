package com.soon83.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soon83.domain.AuthService;
import com.soon83.exception.CustomAuthenticationEntryPoint;
import com.soon83.exception.ExceptionHandlerFilter;
import com.soon83.jwt.AuthFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    public static final String GET_AUTH_TOKEN_URL = "/api/v1/auth";
    public static final String GET_REFRESH_TOKEN_URL = "/api/v1/refresh";
    private final AuthService authService;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
        var authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
        AuthFilter authFilter = new AuthFilter(authenticationManager, objectMapper);
        ExceptionHandlerFilter exceptionHandlerFilter = new ExceptionHandlerFilter(objectMapper);
        var authenticationEntryPoint = new CustomAuthenticationEntryPoint();

        http
                .authenticationManager(authenticationManager)
                .headers(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, GET_AUTH_TOKEN_URL).permitAll()
                        .requestMatchers(HttpMethod.POST, GET_REFRESH_TOKEN_URL).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(exceptionHandlerFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterAt(authFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(handler -> handler.authenticationEntryPoint(authenticationEntryPoint))
        ;
        return http.build();
    }
}
