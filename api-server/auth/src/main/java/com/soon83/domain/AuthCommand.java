package com.soon83.domain;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class AuthCommand {

    @Data
    @NoArgsConstructor
    public static class AuthRequest {
        @NotBlank(message = "필수값")
        private String memberEmail;
        @NotBlank(message = "필수값")
        private String memberPassword;

        @Builder
        public AuthRequest(
                String memberEmail,
                String memberPassword
        ) {
            this.memberEmail = memberEmail;
            this.memberPassword = memberPassword;
        }
    }

    @Data
    @NoArgsConstructor
    public static class RefreshRequest {
        @NotBlank(message = "필수값")
        private String memberEmail;
        @NotBlank(message = "필수값")
        private String memberAuthToken;
        @NotBlank(message = "필수값")
        private String memberRefreshToken;

        @Builder
        public RefreshRequest(
                String memberEmail,
                String memberAuthToken,
                String memberRefreshToken
        ) {
            this.memberEmail = memberEmail;
            this.memberAuthToken = memberAuthToken;
            this.memberRefreshToken = memberRefreshToken;
        }
    }
}