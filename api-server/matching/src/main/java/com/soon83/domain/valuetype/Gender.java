package com.soon83.domain.valuetype;

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
}
