package com.alibaba.maxgraph.compiler.custom.output;


import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.tinkerpop.gremlin.process.traversal.Traverser;

import java.io.Serializable;
import java.util.function.Function;

public class OutputOdpsFunction<IN> implements Function<Traverser<IN>, Long>, Serializable {
    private static final long serialVersionUID = -2502354835622595109L;
    private OutputOdpsTable outputOdpsTable;

    public OutputOdpsFunction(OutputOdpsTable outputOdpsTable) {
        this.outputOdpsTable = outputOdpsTable;
    }

    @Override
    public Long apply(Traverser<IN> traverser) {
        throw new NotImplementedException("apply");
    }

    public OutputOdpsTable getOutputOdpsTable() {
        return outputOdpsTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OutputOdpsFunction<?> that = (OutputOdpsFunction<?>) o;
        return Objects.equal(outputOdpsTable, that.outputOdpsTable);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(outputOdpsTable);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .toString();
    }
}
