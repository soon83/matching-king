package com.soon83.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional(readOnly = true)
    public AuthQuery.AuthAdaptor loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        /*var member = memberQueryService.readMemberByEmail(memberEmail);
        var loginInfo = new AuthQuery.Main(member);
        return new AuthQuery.AccountAdaptor(loginInfo);*/
        return new AuthQuery.AuthAdaptor(
                AuthQuery.Info.builder()
                        .memberEmail("admin@email.com")
                        .memberPassword(passwordEncoder.encode("1234"))
                        .memberRole(AuthQuery.Role.ADMIN)
                        .build()
        );
    }
}
