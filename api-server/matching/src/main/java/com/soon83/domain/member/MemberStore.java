package com.soon83.domain.member;

public interface MemberStore {
    Member create(Member member);
    void update(Member member);
    void delete(Member member);
}
