package com.alibaba.maxgraph.compiler.custom;

public final class Lists {
    public static <V> ListPredicate contains(V value) {
        return new ListPredicate<>(value, ListMatchType.LIST_CONTAINS);
    }

    public static <V> ListKeyPredicate contains(String key, V value) {
        return new ListKeyPredicate<>(key, value, ListMatchType.LIST_CONTAINS);
    }

    public static <V> ListPredicate containsAny(java.util.List<V> valueList) {
        return new ListPredicate<>(valueList, ListMatchType.LIST_CONTAINS_ANY);
    }

    public static <V> ListKeyPredicate containsAny(String key, java.util.List<V> valueList) {
        return new ListKeyPredicate<>(key, valueList, ListMatchType.LIST_CONTAINS_ANY);
    }

    public static <V> ListPredicate containsAll(java.util.List<V> valueList) {
        return new ListPredicate<>(valueList, ListMatchType.LIST_CONTAINS_ALL);
    }

    public static <V> ListKeyPredicate containsAll(String key, java.util.List<V> valueList) {
        return new ListKeyPredicate<>(key, valueList, ListMatchType.LIST_CONTAINS_ALL);
    }

    public static <V> java.util.List<V> of(V... val) {
        return com.google.common.collect.Lists.newArrayList(val);
    }
}
