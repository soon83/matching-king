package com.soon83.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soon83.domain.auth.AuthUserService;
import com.soon83.exception.CustomAuthenticationEntryPoint;
import com.soon83.exception.ExceptionHandlerFilter;
import com.soon83.jwt.AuthCheckFilter;
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
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final AuthUserService authUserService;
    private final ObjectMapper objectMapper;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain jwtFilterChain(HttpSecurity http) throws Exception {
        var authenticationManager = http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(authUserService)
                .passwordEncoder(passwordEncoder)
                .and()
                .build();
        AuthCheckFilter authCheckFilter = new AuthCheckFilter(authenticationManager, authUserService, objectMapper);
        ExceptionHandlerFilter exceptionHandlerFilter = new ExceptionHandlerFilter(objectMapper);
        var authenticationEntryPoint = new CustomAuthenticationEntryPoint();

        http
                .authenticationManager(authenticationManager)
                .headers(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/v1/members/*/auth").permitAll()
                        .requestMatchers("/api/v1/codes", "/api/v1/codes/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(exceptionHandlerFilter, BasicAuthenticationFilter.class)
                .addFilterAt(authCheckFilter, BasicAuthenticationFilter.class)
                .exceptionHandling(handler -> handler.authenticationEntryPoint(authenticationEntryPoint))
        ;
        return http.build();
    }
}
