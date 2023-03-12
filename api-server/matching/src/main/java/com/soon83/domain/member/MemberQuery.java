package com.soon83.domain.member;

import com.soon83.domain.limit.model.LimitQuery;
import com.soon83.domain.member.condition.MemberConditionQuery;
import com.soon83.domain.member.matchingcondition.MemberMatchingConditionQuery;
import lombok.Builder;
import lombok.Data;

public class MemberQuery {

    @Data
    public static class SearchCondition {
        private String email;
        private String nickname;
        private Member.Gender gender;
        private Member.Mbti mbti;
        private Member.Type type;
        private Member.Role role;

        @Builder
        public SearchCondition(
                String email,
                String nickname,
                Member.Gender gender,
                Member.Mbti mbti,
                Member.Type type,
                Member.Role role
        ) {
            this.email = email;
            this.nickname = nickname;
            this.gender = gender;
            this.mbti = mbti;
            this.type = type;
            this.role = role;
        }
    }

    @Data
    public static class Main {
        private final Long id;
        private final String email;
        private final String nickname;
        private final Member.Gender gender;
        private final Member.Mbti mbti;
        private final Member.Type type;
        private final Member.Role role;

        @Builder
        public Main(
                Long id,
                String email,
                String nickname,
                Member.Gender gender,
                Member.Mbti mbti,
                Member.Type type,
                Member.Role role
        ) {
            this.id = id;
            this.email = email;
            this.nickname = nickname;
            this.gender = gender;
            this.mbti = mbti;
            this.type = type;
            this.role = role;
        }

        public Main(Member entity) {
            this.id = entity.getId();
            this.email = entity.getEmail();
            this.nickname = entity.getNickname();
            this.gender = entity.getGender();
            this.mbti = entity.getMbti();
            this.type = entity.getType();
            this.role = entity.getRole();
        }
    }

    @Data
    public static class Detail {
        private final Long id;
        private final String email;
        private final String nickname;
        private final Member.Gender gender;
        private final Member.Mbti mbti;
        private final Member.Type type;
        private final Member.Role role;
        private final LimitQuery.Main limit;
        private final MemberConditionQuery.Main memberCondition;
        private final MemberMatchingConditionQuery.Main memberMatchingCondition;

        @Builder
        public Detail(Member entity) {
            this.id = entity.getId();
            this.email = entity.getEmail();
            this.nickname = entity.getNickname();
            this.gender = entity.getGender();
            this.mbti = entity.getMbti();
            this.type = entity.getType();
            this.role = entity.getRole();
            this.limit = new LimitQuery.Main(entity.getLimit());
            this.memberCondition = new MemberConditionQuery.Main(entity.getMemberCondition());
            this.memberMatchingCondition = new MemberMatchingConditionQuery.Main(entity.getMemberMatchingCondition());
        }
    }
}
