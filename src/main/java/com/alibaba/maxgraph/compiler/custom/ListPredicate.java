package com.alibaba.maxgraph.compiler.custom;

import org.apache.tinkerpop.gremlin.process.traversal.Traversal;

import java.util.Objects;

public class ListPredicate<S, E, V> extends CustomPredicate<S, E> implements NegatePredicate {
    private static final long serialVersionUID = -7761400220204706449L;
    private V listValue;
    private ListMatchType matchType;
    private boolean negate = false;

    public ListPredicate() {
        super(null, null);
        setPredicateType(PredicateType.LIST);
    }

    public ListPredicate(V value, ListMatchType matchType) {
        super(null, null);
        this.listValue = value;
        this.matchType = matchType;
        setPredicateType(PredicateType.LIST);
    }

    public V getListValue() {
        return listValue;
    }

    public ListMatchType getMatchType() {
        return matchType;
    }

    public Traversal<S, E> getOriginalValue() {
        throw new UnsupportedOperationException("Not this operation in gremlin=>getOriginalValue");
    }

    public Traversal<S, E> getValue() {
        throw new UnsupportedOperationException("Not this operation in gremlin=>getValue");
    }

    public void setValue(Traversal<S, E> value) {
        throw new UnsupportedOperationException("Not this operation in gremlin=>setValue");
    }

    @Override
    public boolean test(Traversal<S, E> traversal) {
        throw new UnsupportedOperationException("Not this operation in gremlin=>test");
    }

    @Override
    public String toString() {
        return String.valueOf(matchType) + "(" + listValue + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(listValue, matchType, negate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListPredicate<?, ?, ?> that = (ListPredicate<?, ?, ?>) o;
        return com.google.common.base.Objects.equal(listValue, that.listValue) &&
                matchType == that.matchType &&
                negate == that.negate;
    }

    @Override
    public boolean isNegate() {
        return this.negate;
    }

    @Override
    public ListPredicate<S, E, V> negate() {
        this.negate = true;
        return this;
    }
}
