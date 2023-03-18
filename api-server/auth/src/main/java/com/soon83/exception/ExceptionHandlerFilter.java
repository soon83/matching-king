package com.soon83.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soon83.CommonErrorResponse;
import com.soon83.exception.auth.AuthMethodNotAllowedException;
import com.soon83.exception.auth.AuthMemberNotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    private final ObjectMapper objectMapper;

    public ExceptionHandlerFilter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        } catch (AuthMethodNotAllowedException e) {
            log.error("[MethodNotAllowedException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
            setErrorResponse(response, e.getErrorCode());
        } catch (AuthMemberNotFoundException e) {
            log.error("[AuthenticationException] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
            setErrorResponse(response, e.getErrorCode());
        } catch (Exception e) {
            log.error("[Exception] cause: {}, message: {}", NestedExceptionUtils.getMostSpecificCause(e), e.getMessage());
            setErrorResponse(response, ErrorCode.UNAUTHORIZED_ERROR);
        }
        /*catch (ExpiredJwtException e) {
            //토큰의 유효기간 만료
            setErrorResponse(response, ErrorCode.TOKEN_EXPIRED);
        } catch (JwtException | IllegalArgumentException e) {
            //유효하지 않은 토큰
            setErrorResponse(response, ErrorCode.INVALID_TOKEN);
        }*/
    }

    private void setErrorResponse(HttpServletResponse response, ErrorCode errorCode) {
        response.setStatus(errorCode.getStatus());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());

        try {
            CommonErrorResponse errorResponse = CommonErrorResponse.of(errorCode.getStatus(), errorCode.getCode(), errorCode.getMessage());
            objectMapper.writeValue(response.getWriter(), errorResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}