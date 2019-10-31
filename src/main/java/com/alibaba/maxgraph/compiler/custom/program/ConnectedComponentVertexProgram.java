package com.alibaba.maxgraph.compiler.custom.program;

import org.apache.tinkerpop.gremlin.process.computer.GraphComputer;
import org.apache.tinkerpop.gremlin.process.computer.Memory;
import org.apache.tinkerpop.gremlin.process.computer.MessageScope;
import org.apache.tinkerpop.gremlin.process.computer.Messenger;
import org.apache.tinkerpop.gremlin.process.computer.VertexProgram;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.Set;

/**
 * @author kabanowakanoboru
 */
public class ConnectedComponentVertexProgram implements CustomProgram, VertexProgram {
    @Override
    public void setup(Memory memory) {

    }

    @Override
    public void execute(Vertex vertex, Messenger messenger, Memory memory) {

    }

    @Override
    public boolean terminate(Memory memory) {
        return false;
    }

    @Override
    public Set<MessageScope> getMessageScopes(Memory memory) {
        return null;
    }

    @Override
    public VertexProgram clone() {
        return null;
    }

    @Override
    public GraphComputer.ResultGraph getPreferredResultGraph() {
        return null;
    }

    @Override
    public GraphComputer.Persist getPreferredPersist() {
        return null;
    }
}
