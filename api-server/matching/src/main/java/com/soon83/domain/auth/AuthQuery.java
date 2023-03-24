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
                    authUser.getEmail(),
                    authUser.getPassword(),
                    authorities(authUser.getRole().name())
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
        private Long id;
        private String email;
        private String password;
        private String nickname;
        private Member.Role role;

        @Builder
        public Info(
                Long id,
                String email,
                String password,
                String nickname,
                Member.Role role
        ) {
            this.id = id;
            this.email = email;
            this.password = password;
            this.nickname = nickname;
            this.role = role;
        }

        public Info(Member entity) {
            this.id = entity.getId();
            this.email = entity.getEmail();
            this.password = entity.getPassword();
            this.nickname = entity.getNickname();
            this.role = entity.getRole();
        }
    }
}