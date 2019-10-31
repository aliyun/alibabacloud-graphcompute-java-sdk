package com.alibaba.maxgraph.examples;

import com.alibaba.maxgraph.graph.CompositeId;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;

public class RealtimeExample extends AbstractExample {
    @Override
    void execute() {
        // Add person vertex v1/v2/v3
        ResultSet v1result = this.getClient().submit("graph.addVertex(T.label, 'person', 'id', 1, 'name', 'tom', 'age', 20)");
        Vertex v1 = v1result.one().getVertex();
        System.out.println(v1.id().toString());

        ResultSet v2result = this.getClient().submit("graph.addVertex(T.label, 'person', 'id', 2, 'name', 'jack', 'age', 30)");
        Vertex v2 = v2result.one().getVertex();
        System.out.println(v2.id().toString());

        ResultSet v3result = this.getClient().submit("graph.addVertex(T.label, 'person', 'id', 3, 'name', 'tony', 'age', 25)");
        Vertex v3 = v3result.one().getVertex();
        System.out.println(v3.id().toString());

        // Add edge from v1->v2
        ResultSet e1result = this.getClient().submit("graph.addEdge(" +
                "'" + v1.id().toString() + "'," +
                "'" + v2.id().toString() + "'," +
                "T.label,'knows'," +
                "'id',1," +
                "'weight',0.5)");
        Edge e1 = e1result.one().getEdge();
        System.out.println(e1.toString());

        // Add edge from v2->v3
        ResultSet e2result = this.getClient().submit("graph.addEdge(" +
                "'" + v2.id().toString() + "'," +
                "'" + v3.id().toString() + "'," +
                "T.label,'knows'," +
                "'id',2," +
                "'weight',0.8)");
        Edge e2 = e2result.one().getEdge();
        System.out.println(e2.toString());


        //for current version, there exists synchronization latency of schema meta data
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Update vertex v1's age to 21 by label and primary key
        ResultSet v1UpdateResult = this.getClient().submit("graph.updateVertex(T.label, 'person', 'id', 1, 'age', 25)");
        Vertex v1Update = v1UpdateResult.one().getVertex();
        System.out.println(v1Update.id().toString());

        // Update edge e1's weight
        ResultSet e1UpdateResult = this.getClient().submit("graph.updateEdge('knows'," +
                ((CompositeId) e1.id()).id() + "," +
                "'" + v1.id().toString() + "'," +
                "'" + v2.id().toString() + "'," +
                "'weight', 0.75)");
        System.out.println(e1UpdateResult.one().getString());

        // Delete vertex v3 by vertex id, notice that the edge related to v3 such as e2 won't be deleted, you shoud delete it manually
        ResultSet v3DeleteResult = this.getClient().submit("graph.deleteVertex('" + v3.id().toString() + "')");
        System.out.println(v3DeleteResult.one().getString());

        // Delete edge e1
        ResultSet e1DeleteResult = this.getClient().submit("graph.deleteEdge('knows'," +
                ((CompositeId) e1.id()).id() + "," +
                "'" + v1.id().toString() + "'," +
                "'" + v2.id().toString() + "')");
        System.out.println(e1DeleteResult.one().getString());
    }

    public static void main(String[] args) {
        SchemaExample schemaExample = new SchemaExample();
        try {
            schemaExample.createVertexType();
            schemaExample.createEdgeType();
        } finally {
            schemaExample.close();
        }

        RealtimeExample realtimeExample = new RealtimeExample();
        try {
            realtimeExample.execute();
        } finally {
            realtimeExample.close();
        }

    }
}
