package com.alibaba.maxgraph.compiler.custom;

import java.util.Objects;

public class ListKeyPredicate<S, E, V> extends ListPredicate<S, E, V> {
    private static final long serialVersionUID = -4500926366087508080L;
    private String key;

    public ListKeyPredicate() {
        super();
    }

    public ListKeyPredicate(String key, V value, ListMatchType matchType) {
        super(value, matchType);
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    @Override
    public String toString() {
        return "key(" + key + ")" + super.toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, getListValue(), getMatchType(), getPredicateType(), isNegate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListKeyPredicate<?, ?, ?> that = (ListKeyPredicate<?, ?, ?>) o;
        return com.google.common.base.Objects.equal(key, that.key) &&
                super.equals(o);
    }
}
