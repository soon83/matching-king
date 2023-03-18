package com.soon83;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

public class JwtUtil {
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("DidierDrogba");
    private static final long AUTH_TIME = 60 * 30; // 30분
    private static final long REFRESH_TIME = 60 * 60 * 24 * 14; // 2주

    public static String makeAuthToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withClaim("exp", Instant.now().getEpochSecond() + AUTH_TIME)
                .sign(ALGORITHM);
    }

    public static String makeRefreshToken(String username) {
        return JWT.create()
                .withSubject(username)
                .withClaim("exp", Instant.now().getEpochSecond() + REFRESH_TIME)
                .sign(ALGORITHM);
    }

    public static JwtVerifyResult verify(String token) {
        try {
            DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
            return JwtVerifyResult.builder()
                    .success(true)
                    .username(verify.getSubject())
                    .build();
        } catch (Exception ex) {
            DecodedJWT decode = JWT.decode(token);
            return JwtVerifyResult.builder()
                    .success(false)
                    .username(decode.getSubject())
                    .build();
        }
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JwtVerifyResult {
        private boolean success;
        private String username;
    }
}
