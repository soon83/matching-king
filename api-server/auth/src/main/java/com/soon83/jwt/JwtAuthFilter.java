package com.soon83.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soon83.CommonResponse;
import com.soon83.domain.AuthQuery;
import com.soon83.exception.auth.AuthMethodNotAllowedException;
import com.soon83.exception.auth.AuthMemberNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.soon83.config.SecurityConfig.AUTH;

@Slf4j
public class JwtAuthFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;

    public JwtAuthFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        super(authenticationManager);
        setFilterProcessesUrl(AUTH);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            log.error("# Authentication method not supported: {}", request.getMethod());
            throw new AuthMethodNotAllowedException();
        }

        AuthQuery.Info authRequest;
        try {
            authRequest = objectMapper.readValue(request.getInputStream(), AuthQuery.Info.class);

        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        var authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getMemberEmail(), authRequest.getMemberPassword(), null);
        try {
            Authentication authenticate = getAuthenticationManager().authenticate(authenticationToken);
            return authenticate;
        } catch (AuthenticationException e) {
            throw new AuthMemberNotFoundException();
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AuthQuery.AuthAdaptor principal = (AuthQuery.AuthAdaptor) authResult.getPrincipal();
        AuthQuery.Info authUser = principal.getAuthUser();

        response.setStatus(HttpServletResponse.SC_OK);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setHeader("access_token", JwtUtil.makeAuthToken(authUser.getMemberEmail()));
        response.setHeader("refresh_token", JwtUtil.makeRefreshToken(authUser.getMemberEmail()));

        objectMapper.writeValue(response.getWriter(), CommonResponse.success(new AuthQuery.Main(authUser)));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        throw new IllegalArgumentException();
    }
}
