package com.soon83.domain.auth;

import com.soon83.domain.member.Member;
import com.soon83.domain.member.MemberReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MemberReader memberReader;

    @Override
    @Transactional(readOnly = true)
    public AuthQuery.AuthAdaptor loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Member member = memberReader.readByEmail(memberEmail);
        return new AuthQuery.AuthAdaptor(AuthQuery.Info.builder()
                .memberId(member.getId())
                .memberEmail(member.getEmail())
                .memberPassword(member.getPassword())
                .memberRole(member.getRole())
                .build());
    }
}
