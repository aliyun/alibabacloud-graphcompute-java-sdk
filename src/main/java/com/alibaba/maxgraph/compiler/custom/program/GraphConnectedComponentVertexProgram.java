package com.alibaba.maxgraph.compiler.custom.program;

import com.google.common.collect.Lists;
import org.apache.tinkerpop.gremlin.process.computer.GraphComputer;
import org.apache.tinkerpop.gremlin.process.computer.Memory;
import org.apache.tinkerpop.gremlin.process.computer.MessageScope;
import org.apache.tinkerpop.gremlin.process.computer.Messenger;
import org.apache.tinkerpop.gremlin.process.computer.VertexProgram;
import org.apache.tinkerpop.gremlin.structure.Vertex;

import java.util.List;
import java.util.Set;

public class GraphConnectedComponentVertexProgram implements CustomProgram, VertexProgram {
    private List<String> outputList = Lists.newArrayList();
    private int iteration = 0;

    public GraphConnectedComponentVertexProgram output(String... list) {
        outputList.addAll(Lists.newArrayList(list));

        return this;
    }

    public GraphConnectedComponentVertexProgram iteration(int iteration) {
        if (iteration <= 0) {
            throw new IllegalArgumentException("cant set iteration <= 0");
        }
        this.iteration = iteration;
        return this;
    }

    public List<String> getOutputList() {
        return outputList;
    }

    public int getIteration() {
        return this.iteration;
    }

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
