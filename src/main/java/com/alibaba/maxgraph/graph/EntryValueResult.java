package com.alibaba.maxgraph.graph;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import java.util.Map;

public class EntryValueResult implements QueryResult, Map.Entry {
    private QueryResult key;
    private QueryResult value;

    public EntryValueResult() {
    }

    public QueryResult getKey() {
        return key;
    }

    public void setKey(QueryResult key) {
        this.key = key;
    }

    public QueryResult getValue() {
        return value;
    }

    @Override
    public Object setValue(Object value) {
        throw new UnsupportedOperationException();
    }

    public void setValue(QueryResult value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntryValueResult that = (EntryValueResult) o;
        return Objects.equal(key, that.key) &&
                Objects.equal(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(key, value);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("key", key)
                .add("value", value)
                .toString();
    }
}
