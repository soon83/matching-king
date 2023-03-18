package com.soon83.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soon83.CommonErrorResponse;
import com.soon83.JwtUtil;
import com.soon83.domain.auth.AuthQuery;
import com.soon83.domain.auth.AuthService;
import com.soon83.exception.ErrorCode;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class AuthCheckFilter extends BasicAuthenticationFilter {
    private final AuthService authService;
    private final ObjectMapper objectMapper;

    public AuthCheckFilter(AuthenticationManager authenticationManager, AuthService authService, ObjectMapper objectMapper) {
        super(authenticationManager);
        this.authService = authService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String bearer = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (bearer == null || !bearer.startsWith(JwtUtil.BEARER_TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }
        String token = bearer.substring(JwtUtil.BEARER_TOKEN_PREFIX.length());
        var result = JwtUtil.verify(token);
        if (result.isSuccess()) {
            AuthQuery.AuthAdaptor authRequest = (AuthQuery.AuthAdaptor) authService.loadUserByUsername(result.getUsername());
            var authRequestToken = new UsernamePasswordAuthenticationToken(authRequest.getAuthUser(), null, authRequest.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authRequestToken);
            chain.doFilter(request, response);
        } else {
            ErrorCode errorCode = ErrorCode.COMMON_INVALID_TOKEN_ERROR;

            response.setStatus(errorCode.getStatus());
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setCharacterEncoding(StandardCharsets.UTF_8.name());

            objectMapper.writeValue(response.getWriter(), CommonErrorResponse.of(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage()));
        }
    }
}