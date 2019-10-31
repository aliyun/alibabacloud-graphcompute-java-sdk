package com.alibaba.maxgraph.compiler.custom.map;

public class Mapper {
    public static RangeSumFunction rangeSum(String propName, int start, int count) {
        return new RangeSumFunction(propName, start, count);
    }
}
