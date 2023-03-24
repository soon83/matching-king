package com.soon83.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soon83.CommonResponse;
import com.soon83.JwtUtil;
import com.soon83.domain.AuthCommand;
import com.soon83.domain.AuthQuery;
import com.soon83.exception.auth.AuthMemberNotFoundException;
import com.soon83.exception.auth.AuthMethodNotAllowedException;
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

import static com.soon83.config.SecurityConfig.GET_AUTH_TOKEN_URL;

@Slf4j
public class AuthFilter extends UsernamePasswordAuthenticationFilter {
    private final ObjectMapper objectMapper;

    public AuthFilter(AuthenticationManager authenticationManager, ObjectMapper objectMapper) {
        super(authenticationManager);
        setFilterProcessesUrl(GET_AUTH_TOKEN_URL);
        this.objectMapper = objectMapper;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (!request.getMethod().equals("POST")) {
            throw new AuthMethodNotAllowedException();
        }

        AuthCommand.AuthRequest authRequest;
        try {
            authRequest = objectMapper.readValue(request.getInputStream(), AuthCommand.AuthRequest.class);
        } catch (IOException e) {
            throw new IllegalArgumentException();
        }

        var authenticationToken = new UsernamePasswordAuthenticationToken(authRequest.getMemberEmail(), authRequest.getMemberPassword(), null);
        try {
            return getAuthenticationManager().authenticate(authenticationToken);
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
        JwtUtil.issueTokenToResponseHeader(authUser.getMemberEmail(), authUser.getMemberId(), authUser.getMemberNickname(), response);

        objectMapper.writeValue(response.getWriter(), CommonResponse.success(new AuthQuery.Main(authUser)));
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        throw new IllegalArgumentException();
    }
}
