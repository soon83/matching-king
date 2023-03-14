package com.soon83.infrastructure;

import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.*;
import org.springframework.util.ObjectUtils;

import java.util.List;

public class CustomExpressionUtil {

    public static OrderSpecifier<Double> rand() {
        return Expressions.numberTemplate(Double.class, "function('rand')").asc();
    }

    public static BooleanExpression eq(BooleanPath domainValue, Boolean value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    public static BooleanExpression ne(BooleanPath domainValue, Boolean value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.ne(value);
    }

    public static BooleanExpression eq(StringPath domainValue, String value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    public static BooleanExpression ne(StringPath domainValue, String value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.ne(value);
    }

    public static <T extends Number & Comparable<?>> BooleanExpression eq(NumberPath<T> domainValue, T value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.eq(value);
    }

    public static <T extends Number & Comparable<?>> BooleanExpression ne(NumberPath<T> domainValue, T value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.ne(value);
    }

    public static <T extends Enum<T>> BooleanExpression in(EnumPath<T> domainValue, List<T> valueList) {
        if (ObjectUtils.isEmpty(valueList)) return null;
        return domainValue.in(valueList);
    }

    public static <T extends Enum<T>> BooleanExpression notIn(EnumPath<T> domainValue, List<T> valueList) {
        if (ObjectUtils.isEmpty(valueList)) return null;
        return domainValue.notIn(valueList);
    }

    public static <T extends Number & Comparable<?>> BooleanExpression between(NumberPath<T> domainValue, T start, T end) {
        if (ObjectUtils.isEmpty(start) || ObjectUtils.isEmpty(end)) return null;
        return domainValue.goe(start).and(domainValue.loe(end));
    }

    public static <T extends Comparable> BooleanExpression between(DateTimePath<T> domainValue, T start, T end) {
        if (ObjectUtils.isEmpty(start) || ObjectUtils.isEmpty(end)) return null;
        return domainValue.goe(start).and(domainValue.loe(end));
    }

    public static <T extends Comparable> BooleanExpression goe(DateTimePath<T> domainValue, T value) {
        if (ObjectUtils.isEmpty(value)) return null;
        return domainValue.goe(value);
    }
}
