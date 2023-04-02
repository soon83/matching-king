package com.soon83.domain.valuetype;

import com.soon83.domain.member.Member;
import jakarta.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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

    public List<Member.Gender> checkedList() {
        List<Member.Gender> genders = new ArrayList<>();
        if (this.male) genders.add(Member.Gender.MALE);
        if (this.female) genders.add(Member.Gender.FEMALE);
        return genders;
    }
}
