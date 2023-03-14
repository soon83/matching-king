package com.soon83.infrastructure;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;
import java.util.List;

public class BooleanExpressionUtil {



    private static OrderSpecifier<Double> randomOrder() {
        return Expressions.numberTemplate(Double.class, "function('rand')").asc();
    }

    public static BooleanExpression eq(BooleanPath domainValue, Boolean value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    public static <T extends Number & Comparable<?>> BooleanExpression eq(NumberPath<T> domainValue, T value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    private static <T extends Enum<T>> BooleanExpression in(EnumPath<T> domainValue, List<T> valueList) {
        if (ObjectUtils.isEmpty(valueList)) return null;
        return domainValue.in(valueList);
    }

    private static <T extends Number & Comparable<?>> BooleanExpression between(NumberPath<T> domainValue, T start, T end) {
        if (ObjectUtils.isEmpty(start) || ObjectUtils.isEmpty(end)) return null;
        return domainValue.goe(start).and(domainValue.loe(end));
    }

    public static BooleanExpression eq(NumberPath<Long> domainValue, Long value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    public static BooleanExpression goe(DateTimePath<LocalDateTime> domainValue, LocalDateTime value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.goe(value);
    }
}
