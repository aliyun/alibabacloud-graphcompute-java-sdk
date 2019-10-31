package com.alibaba.maxgraph.examples;

import org.apache.tinkerpop.gremlin.driver.ResultSet;

/**
 * Schema manager
 */
public class SchemaExample extends AbstractExample {
    public static void main(String[] args) {
        SchemaExample schemaExample = new SchemaExample();
        try {
            schemaExample.execute();
        } finally {
            schemaExample.close();
        }
    }

    @Override
    public void execute() {
        createVertexType();
        createEdgeType();
        alterVertexEdgeType();
        getSchemaInfo();
        dropVertexEdgeType();
    }

    void createVertexType() {
        // Create person vertex
        // We support int/long/string/double/list<int>/list<long>/list<string> data type for property
        ResultSet createVertex = this.getClient().submit("graph.createVertexType('person')" +
                ".addProperty('id','long')" +
                ".addProperty('name','string', 'comment message of name')" +    // Add property with comment
                ".addProperty('age', 'int')" +
//                ".comment('table comment')" + // Add comment of this table
                ".primaryKey('id')");
        String createVertexMessage = createVertex.one().getString();
        System.out.println(createVertexMessage);
    }

    void createEdgeType() {
        // Create person knows edge from person vertex type to person vertex type,
        // there can be more than one addRelation when create edge type
        ResultSet createEdge = this.getClient().submit("graph.createEdgeType('knows')" +
                ".addProperty('id', 'long')" +
                ".addProperty('weight', 'double', 'weight of knows', 1.0)" +    // Add property with default value
                ".addRelation('person', 'person')");
        String createEdgeMessage = createEdge.one().getString();
        System.out.println(createEdgeMessage);
    }

    private void alterVertexEdgeType() {
        // Add or remove vertex/edge's property
        ResultSet alterVertex = this.getClient().submit("graph.alterVertexEdgeType('person')" +
                ".addProperty('age2','int', 'age2 property')" +
                ".addProperty('age3','int', 'age3 property')");
        System.out.println(alterVertex.one().getString());

        ResultSet dropVertexProperty = this.getClient().submit("graph.alterVertexEdgeType('person')" +
                ".dropProperty('age2')" +
                ".dropProperty('age3')");
        System.out.println(dropVertexProperty.one().getString());

        ResultSet alterEdge = this.getClient().submit("graph.alterVertexEdgeType('knows')" +
                ".addProperty('test1','int', 'age property')" +
                ".dropProperty('weight')");
        System.out.println(alterEdge.one().getString());

        ResultSet dropEdgeProperty = this.getClient().submit("graph.alterVertexEdgeType('knows')" +
                ".dropProperty('test1')" +
                ".addProperty('weight', 'double')");
        System.out.println(dropEdgeProperty.one().getString());

        // Alter relation in edge, there can be more than one dropRelation in one command
        ResultSet removeRelation = this.getClient().submit("graph.alterEdgeRelation('knows')" +
                ".dropRelation('person','person')");
        System.out.println(removeRelation.one().toString());

        ResultSet addRelation = this.getClient().submit("graph.alterEdgeRelation('knows')" +
                ".addRelation('person','person')");
        System.out.println(addRelation.one().toString());
    }

    private void dropVertexEdgeType() {
        // Drop vertex or edge type
        ResultSet dropVertex = this.getClient().submit("graph.dropVertexEdgeType('person')");
        System.out.println(dropVertex.one().getString());

        ResultSet dropEdge = this.getClient().submit("graph.dropVertexEdgeType('knows')");
        System.out.println(dropEdge.one().getString());
    }

    private void getSchemaInfo() {
        // Get the json format of schema
        ResultSet schemaInfo = this.getClient().submit("graph.schema()");
        System.out.println(schemaInfo.one().getString());
    }
}
