package com.alibaba.maxgraph.credentials;

public class CredentialsManager {
    private Credentials credentials;

    public CredentialsManager(String accessKeyId, String accessKeySecret) {
        credentials = new DefaultCredentials(accessKeyId, accessKeySecret);
    }

    public String getUserName() {
        return this.credentials.getAccessKeyId();
    }

    public String getPassword() throws Exception {
        return SignUtil.generateAuthURLParameter(this.credentials);
    }
}
