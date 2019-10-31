package com.alibaba.maxgraph.compiler.custom.map;

import org.apache.tinkerpop.gremlin.process.traversal.Traverser;

import java.io.Serializable;
import java.util.Map;
import java.util.function.Function;

import static com.google.common.base.Preconditions.checkArgument;

public class RangeSumFunction<VALUE> implements Function<Traverser<VALUE>, Traverser<Map<VALUE, Double>>>, Serializable {
    private static final long serialVersionUID = -6037294006085152014L;

    private String propName;
    private int start;
    private int count;

    public RangeSumFunction(String propName, int start, int count) {
        checkArgument(start >= 0, "start in rangeSum must >= 0");
        checkArgument(count > 0, "start in rangeSum must > 0");
        this.propName = propName;
        this.start = start;
        this.count = count;
    }

    @Override
    public Traverser<Map<VALUE, Double>> apply(Traverser<VALUE> valueTraverser) {
        throw new UnsupportedOperationException();
    }

    public String getPropName() {
        return this.propName;
    }

    public int getStart() {
        return this.start;
    }

    public int getCount() {
        return this.count;
    }
}
