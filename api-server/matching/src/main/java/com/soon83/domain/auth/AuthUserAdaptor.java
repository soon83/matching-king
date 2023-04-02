package com.soon83.domain.auth;

import com.soon83.interfaces.auth.CurrentUser;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
public class AuthUserAdaptor extends User {
    private final AuthUserQuery authUser;

    public AuthUserAdaptor(AuthUserQuery authUser) {
        super(
                authUser.email(),
                authUser.password(),
                authorities(authUser.role().name())
        );
        this.authUser = authUser;
    }

    public CurrentUser getCurrentUser() {
        return new CurrentUser(authUser);
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
