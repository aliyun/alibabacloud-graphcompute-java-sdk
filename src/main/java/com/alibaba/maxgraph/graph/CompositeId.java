package com.alibaba.maxgraph.graph;

import com.google.common.base.Joiner;

public class CompositeId implements ElementId {
    private long id;
    private int typeId;

    public CompositeId() {

    }

    public CompositeId(long id, int typeId) {
        this.id = id;
        this.typeId = typeId;
    }


    @Override
    public long id() {
        return this.id;
    }

    @Override
    public int typeId() {
        return this.typeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) { return true; }
        if (o == null || getClass() != o.getClass()) { return false; }

        CompositeId remoteId = (CompositeId)o;

        if (id != remoteId.id()) { return false; }
        return typeId == remoteId.typeId();
    }

    @Override
    public int hashCode() {
        int result = (int)(id ^ (id >>> 32));
        result = 31 * result + typeId;
        return result;
    }

    @Override
    public String toString() {
        return Joiner.on(".").join(typeId, id);
    }
}