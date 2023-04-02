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
public class AuthUserUserServiceImpl implements AuthUserService {
    private final MemberReader memberReader;

    @Override
    @Transactional(readOnly = true)
    public AuthUserAdaptor loadUserByUsername(String memberEmail) throws UsernameNotFoundException {
        Member member = memberReader.readByEmail(memberEmail);
        AuthUserQuery authMemberInfo = new AuthUserQuery(member);
        return new AuthUserAdaptor(authMemberInfo);
    }
}
