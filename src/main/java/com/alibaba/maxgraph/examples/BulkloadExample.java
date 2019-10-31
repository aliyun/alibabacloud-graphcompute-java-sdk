package com.alibaba.maxgraph.examples;

import com.alibaba.maxgraph.status.BulkStatus;
import com.alibaba.maxgraph.status.StatusResult;
import org.apache.tinkerpop.gremlin.driver.Result;
import org.apache.tinkerpop.gremlin.driver.ResultSet;

/**
 * Bulk load from odps manager
 */
public class BulkloadExample extends AbstractExample {
    private String odpsEndpoint = "***";
    private String yourOdpsAccessId = "***";
    private String yourOdpsAccessKey = "**";

    private String yourBizId = "**";
    private String yourOdpsProject = "**";
    private String yourOdpsVertexTable = "**";
    private String yourOdpsEdgeTable = "**";

    private String odpsPersonIdField = "**";
    private String odpsPersonNameField = "**";
    private String odpsPersonAgeField = "**";

    private String personIdProp = "**";
    private String personNameProp = "**";
    private String personAgeProp = "**";

    private String odpsKnowsIdField = "**";
    private String odpsKnowsWeightField = "**";

    private String knowsIdProp = "**";
    private String knowsWeightProp = "**";

    private String knowsSrcPersonIdField = "**";
    private String knowsDstPersonIdField = "**";

    @Override
    public void execute() {
        try {
            bulkloadVertex();
            bulkloadEdge();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Bulkload person vertex from odps table
     * @throws Exception
     */
    private void bulkloadVertex() throws Exception {
        ResultSet resultSet = this.getClient().submit("graph.bulkloadVertexFromOdps('person')" +
                ".endpoint('" + odpsEndpoint + "')" +
                ".accessId('" + yourOdpsAccessId + "')" +
                ".accessKey('" + yourOdpsAccessKey + "')" +
                ".bizOwnerId('" + yourBizId + "')" +
                ".project('" + yourOdpsProject + "')" +
                ".table('" + yourOdpsVertexTable + "')" +
                //".partition('" + yourOdpsPartition + "')" +
                //".maxInvalidDataCount(10)" + // Default invalid data count is 0
                ".mappingColumn('" + odpsPersonIdField + "', '" + personIdProp + "')" +
                ".mappingColumn('" + odpsPersonNameField + "', '" + personNameProp + "')" +
                ".mappingColumn('" + odpsPersonAgeField + "', '" + personAgeProp + "')" +
                ".signature('" + getCredentialsManager().getPassword() + "')");
        Result result = resultSet.one();
        StatusResult statusResult = StatusResult.fromJsonResult(result.getString());
        if (statusResult.getStatus() == BulkStatus.RUNNING) {
            long jobId = Long.parseLong(statusResult.getData().toString());
            while (true) {
                // Check the job status until it has been finished
                ResultSet statusResultSet = this.getClient().submit("graph.bulkloadJobStatus(" + jobId + ")" +
                        ".signature('" + getCredentialsManager().getPassword() + "')");
                StatusResult currStatusResult = StatusResult.fromJsonResult(statusResultSet.one().getString());
                if (currStatusResult.getStatus() != BulkStatus.RUNNING) {
                    System.out.println("bulkload job status " + currStatusResult.getStatus() +
                            " and message " + currStatusResult.getMessage());
                    break;
                } else {
                    System.out.println("bulkload job status " + currStatusResult.getStatus() +
                            " and message " + currStatusResult.getMessage());
                    Thread.sleep(2000);
                }
            }
        } else if (statusResult.getStatus() == BulkStatus.SUCCESS) {
            System.out.println("bulkload job has been finished");
        } else {
            System.out.println("bulkload job status " + statusResult.getStatus() + " and message " + statusResult.getMessage());
        }
    }

    /**
     * Bulkload knows edge from odps table
     * @throws Exception
     */
    private void bulkloadEdge() throws Exception {
        ResultSet resultSet = this.getClient().submit("graph.bulkloadEdgeFromOdps('knows')" +
                ".endpoint('" + odpsEndpoint + "')" +
                ".accessId('" + yourOdpsAccessId + "')" +
                ".accessKey('" + yourOdpsAccessKey + "')" +
                ".bizOwnerId('" + yourBizId + "')" +
                ".project('" + yourOdpsProject + "')" +
                ".table('" + yourOdpsEdgeTable + "')" +
                //".partition('" + yourOdpsPartition + "')" +
                //".maxInvalidDataCount(10)" + // Default invalid data count is 0
                ".mappingColumn('" + odpsKnowsIdField + "', '" + knowsIdProp + "')" +
                ".mappingColumn('" + odpsKnowsWeightField + "', '" + knowsWeightProp + "')" +
                ".srcVertex('person')" +
                ".mappingSrcPrimaryKey('" + knowsSrcPersonIdField + "','" + personIdProp + "')" +
                ".dstVertex('person')" +
                ".mappingDstPrimaryKey('" + knowsDstPersonIdField + "','" + personIdProp + "')" +
                ".signature('" + getCredentialsManager().getPassword() + "')");
        Result result = resultSet.one();
        StatusResult statusResult = StatusResult.fromJsonResult(result.getString());
        if (statusResult.getStatus() == BulkStatus.RUNNING) {
            long jobId = Long.parseLong(statusResult.getData().toString());
            while (true) {
                // Check the job status until it has been finished
                ResultSet statusResultSet = this.getClient().submit("graph.bulkloadJobStatus(" + jobId + ")" +
                        ".signature('" + getCredentialsManager().getPassword() + "')");
                StatusResult currStatusResult = StatusResult.fromJsonResult(statusResultSet.one().getString());
                if (currStatusResult.getStatus() != BulkStatus.RUNNING) {
                    System.out.println("bulkload job status " + currStatusResult.getStatus() +
                            " and message " + currStatusResult.getMessage());
                    break;
                } else {
                    System.out.println("bulkload job status " + currStatusResult.getStatus() +
                            " and message " + currStatusResult.getMessage());
                    Thread.sleep(2000);
                }
            }
        } else if (statusResult.getStatus() == BulkStatus.SUCCESS) {
            System.out.println("bulkload job has been finished");
        } else {
            System.out.println("bulkload job status " + statusResult.getStatus() + " and message " + statusResult.getMessage());
        }
    }

    public static void main(String[] args) {
        SchemaExample schemaExample = new SchemaExample();
        try {
            schemaExample.createVertexType();
            schemaExample.createEdgeType();
        } finally {
            schemaExample.close();
        }

        BulkloadExample bulkloadExample = new BulkloadExample();
        try {
            bulkloadExample.execute();
        } finally {
            bulkloadExample.close();
        }

    }
}
