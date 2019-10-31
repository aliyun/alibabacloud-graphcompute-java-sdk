package com.alibaba.maxgraph.compiler.custom.program;

import org.apache.tinkerpop.gremlin.process.traversal.P;

public class Program {
    public static ConnectedComponentVertexProgram cc() {
        return new ConnectedComponentVertexProgram();
    }

    public static GraphConnectedComponentVertexProgram graphCC() {
        return new GraphConnectedComponentVertexProgram();
    }

    public static CustomVertexProgram run(String code) {
        return new CustomVertexProgram(code);
    }
}
