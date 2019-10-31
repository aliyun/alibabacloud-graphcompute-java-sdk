package com.alibaba.maxgraph.examples;

import com.alibaba.maxgraph.credentials.CredentialsManager;
import com.alibaba.maxgraph.io.MaxGraphIORegistry;
import org.apache.tinkerpop.gremlin.driver.Client;
import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.MessageSerializer;
import org.apache.tinkerpop.gremlin.driver.ser.GryoMessageSerializerV1d0;
import org.apache.tinkerpop.gremlin.structure.io.gryo.GryoMapper;

public abstract class AbstractExample {
    private Cluster cluster;
    private Client client;
    private CredentialsManager credentialsManager;

    AbstractExample() {
        String instanceDomain = "*****";
        int instanceDomainPort = 0;
        String accessKey = "***";
        String accessSecret = "***";
        credentialsManager = new CredentialsManager(accessKey, accessSecret);

        GryoMapper.Builder kryo = GryoMapper.build().addRegistry(MaxGraphIORegistry.getInstance());
        MessageSerializer serializer = new GryoMessageSerializerV1d0(kryo);
        try {
            cluster = Cluster.build()
                    .addContactPoint(instanceDomain)
                    .port(instanceDomainPort)
                    .serializer(serializer)
                    .credentials(credentialsManager.getUserName(), credentialsManager.getPassword())
                    .create();
        } catch (Exception e) {
            throw new IllegalArgumentException("build credentials password fail", e);
        }
        client = cluster.connect();
    }

    abstract void execute();

    CredentialsManager getCredentialsManager() {
        return credentialsManager;
    }

    Client getClient() {
        return client;
    }

    void close() {
        this.client.close();
        this.cluster.close();
    }
}
