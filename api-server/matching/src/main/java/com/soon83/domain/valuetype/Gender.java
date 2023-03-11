package com.soon83.domain.valuetype;

import com.soon83.domain.member.Member;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Embeddable
public class Gender {

    private boolean male = false;
    private boolean female = false;

    @Builder
    public Gender(
            boolean male,
            boolean female
    ) {
        this.male = male;
        this.female = female;
    }

    public Gender(Member.Gender gender) {
        this.male = gender == Member.Gender.MALE;
        this.female = gender == Member.Gender.FEMALE;
    }
}
