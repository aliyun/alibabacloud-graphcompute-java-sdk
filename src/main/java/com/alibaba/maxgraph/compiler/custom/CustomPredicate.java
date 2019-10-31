package com.alibaba.maxgraph.compiler.custom;

import org.apache.tinkerpop.gremlin.process.traversal.P;
import org.apache.tinkerpop.gremlin.process.traversal.Traversal;

import java.util.function.BiPredicate;

public abstract class CustomPredicate<S, E> extends P<Traversal<S, E>> {
    private PredicateType predicateType;

    public CustomPredicate() {
        super(null, null);
    }

    public CustomPredicate(BiPredicate<Traversal<S, E>, Traversal<S, E>> biPredicate, Traversal<S, E> value) {
        super(null, null);
    }

    public PredicateType getPredicateType() {
        return predicateType;
    }

    public void setPredicateType(PredicateType predicateType) {
        this.predicateType = predicateType;
    }

    @Override
    public BiPredicate<Traversal<S, E>, Traversal<S, E>> getBiPredicate() {
        throw new IllegalArgumentException("no bi predicate in custom predicate");
    }

    public abstract int hashCode();

    public abstract boolean equals(final Object other);
}
