package com.soon83.config;

import com.soon83.domain.auth.AuthUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

@Slf4j
public class SecurityAuditorAware implements AuditorAware<Long> {

    @Override
    public Optional<Long> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        //log.debug("### authentication: {}", authentication);

        if (authentication == null
                || authentication.getPrincipal().equals("anonymousUser")
                || !authentication.isAuthenticated()) {
            return Optional.empty();
        }

        AuthUser authUser = (AuthUser) authentication.getPrincipal();
        return Optional.ofNullable(authUser.getMemberId());
    }
}