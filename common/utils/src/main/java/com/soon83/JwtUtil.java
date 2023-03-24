package com.soon83;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

@Slf4j
public class JwtUtil {
    public static final String BEARER_TOKEN_PREFIX = "Bearer ";
    public static final String AUTH_TOKEN_KEY = "auth_token";
    public static final String REFRESH_TOKEN_KEY = "refresh_token";
    private static final Algorithm ALGORITHM = Algorithm.HMAC256("DidierDrogba");
    private static final long AUTH_TIME = 60 * 30; // 30 분
    private static final long REFRESH_TIME = 60 * 60 * 24 * 14; // 2 주
//    private static final long AUTH_TIME = 10;
//    private static final long REFRESH_TIME = 20;

    public static String makeAuthToken(String username, Long id, String nickname) {
        return makeToken(username, id, nickname, AUTH_TIME);
    }

    public static String makeRefreshToken(String username, Long id, String nickname) {
        return makeToken(username, id, nickname, REFRESH_TIME);
    }

    public static void issueTokenToResponseHeader(String username, Long id, String nickname, HttpServletResponse response) {
        response.setHeader(AUTH_TOKEN_KEY, JwtUtil.makeAuthToken(username, id, nickname));
        response.setHeader(REFRESH_TOKEN_KEY, JwtUtil.makeRefreshToken(username, id, nickname));
    }

    public static JwtVerifyResult verify(String token) {
        try {
            DecodedJWT verify = JWT.require(ALGORITHM).build().verify(token);
            return verifiedResult(true, verify);
        } catch (Exception e) {
            /**
             * AlgorithmMismatchException – if the algorithm stated in the token's header is not equal to the one defined in the JWTVerifier.
             * SignatureVerificationException – if the signature is invalid.
             * TokenExpiredException – if the token has expired.
             * MissingClaimException – if a claim to be verified is missing.
             * IncorrectClaimException – if a claim contained a different value than the expected one.
             */
            log.error("### invalid token exception: {}", e.getMessage());

            DecodedJWT decode = JWT.decode(token);
            return verifiedResult(false, decode);
        }
    }

    private static String makeToken(String username, Long id, String nickname, long refreshTime) {
        return JWT.create()
                .withSubject(username)
                .withClaim("id", id)
                .withClaim("nickname", nickname)
                .withExpiresAt(Instant.now().plusSeconds(refreshTime))
                .sign(ALGORITHM);
    }

    private static JwtVerifyResult verifiedResult(boolean success, DecodedJWT decode) {
        return JwtVerifyResult.builder()
                .success(success)
                .username(decode.getSubject())
                .id(decode.getClaim("id").asLong())
                .nickname(decode.getClaim("nickname").asString())
                .build();
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class JwtVerifyResult {
        private boolean success;
        private String username;
        private Long id;
        private String nickname;
    }
}
