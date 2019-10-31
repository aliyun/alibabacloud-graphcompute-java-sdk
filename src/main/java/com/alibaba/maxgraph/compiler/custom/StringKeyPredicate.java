package com.alibaba.maxgraph.compiler.custom;

import java.util.Objects;

public class StringKeyPredicate<S, E> extends StringPredicate<S, E> {
    private static final long serialVersionUID = -4972271985360884025L;

    private String key;

    public StringKeyPredicate() {
    }

    public StringKeyPredicate(String key, String content, MatchType matchType) {
        super(content, matchType);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "key(" + key + ")." + super.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, getContent(), getMatchType(), isNegate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringKeyPredicate<?, ?> that = (StringKeyPredicate<?, ?>) o;
        return com.google.common.base.Objects.equal(key, that.key) &&
                super.equals(o);
    }
}
