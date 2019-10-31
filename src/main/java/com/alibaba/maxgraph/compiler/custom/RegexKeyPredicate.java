package com.alibaba.maxgraph.compiler.custom;

import java.util.Objects;

public class RegexKeyPredicate<S, E> extends RegexPredicate<S, E> {
    private static final long serialVersionUID = 6074654239797217518L;
    private final String key;

    public RegexKeyPredicate(String key, String regex) {
        super(regex);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, getRegex());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegexKeyPredicate<?, ?> that = (RegexKeyPredicate<?, ?>) o;
        return com.google.common.base.Objects.equal(key, that.key) && super.equals(o);
    }
}
