package com.alibaba.maxgraph.examples;

import com.alibaba.maxgraph.graph.CompositeId;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;

import java.util.Iterator;

/**
 * Query
 */
public class QueryExample extends AbstractExample {
    public static void main(String[] args) {
        QueryExample queryExample = new QueryExample();
        try {
            queryExample.execute();
        } finally {
            queryExample.close();
        }
    }

    @Override
    public void execute() {
        ResultSet results = this.getClient().submit("g.V()");
        for (Result result : results) {
            Vertex vertex = result.getVertex();
            CompositeId id = (CompositeId) vertex.id();
            Iterator<VertexProperty<Object>> propIter = vertex.properties();
            System.out.println("vertex id " + id.toString());
            while (propIter.hasNext()) {
                VertexProperty<Object> prop = propIter.next();
                System.out.println("property key=" + prop.key() + " value=" + prop.value());
            }
        }
    }
}
