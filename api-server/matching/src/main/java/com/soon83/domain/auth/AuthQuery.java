package com.soon83.domain.auth;

import com.soon83.domain.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AuthQuery {

    @Getter
    public static class AuthAdaptor extends User {
        private final AuthQuery.Info authUser;

        public AuthAdaptor(AuthQuery.Info authUser) {
            super(
                    authUser.getMemberEmail(),
                    authUser.getMemberPassword(),
                    authorities(authUser.getMemberRole().name())
            );
            this.authUser = authUser;
        }

        public AuthUser getAuthUser() {
            return new AuthUser(authUser);
        }

        private static void addAuthority(List<GrantedAuthority> authorities, String role) {
            if (role != null && !role.startsWith("ROLE_")) {
                role = "ROLE_" + role;
            }
            authorities.add(new SimpleGrantedAuthority(role));
        }

        private static Collection<? extends GrantedAuthority> authorities(List<String> permissions, String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (String permission : permissions) {
                addAuthority(authorities, permission);
            }
            for (String role : roles) {
                addAuthority(authorities, role);
            }
            return authorities;
        }

        private static Collection<? extends GrantedAuthority> authorities(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            for (String role : roles) {
                addAuthority(authorities, role);
            }
            return authorities;
        }
    }

    @Data
    @NoArgsConstructor
    public static class Info {
        private Long memberId;
        private String memberEmail;
        private String memberPassword;
        private Member.Role memberRole;

        @Builder
        public Info(
                Long memberId,
                String memberEmail,
                String memberPassword,
                Member.Role memberRole
        ) {
            this.memberId = memberId;
            this.memberEmail = memberEmail;
            this.memberPassword = memberPassword;
            this.memberRole = memberRole;
        }
    }
}