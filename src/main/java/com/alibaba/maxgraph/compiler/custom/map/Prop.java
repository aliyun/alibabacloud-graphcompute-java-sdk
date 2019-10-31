package com.alibaba.maxgraph.compiler.custom.map;

public class Prop {
    public static MapPropFillFunction fill(String... propNameList) {
        return new MapPropFillFunction(propNameList);
    }
}
