package com.alibaba.maxgraph.credentials;

import org.apache.commons.lang3.StringUtils;

public class DefaultCredentials implements Credentials {

    private String accessKeyId;
    private String accessKeySecret;

    public DefaultCredentials(String accessKeyId, String accessKeySecret) {
        if (StringUtils.isEmpty(accessKeyId)) {
            throw new IllegalArgumentException("Access key id should not be null or empty.");
        }
        if (StringUtils.isEmpty(accessKeySecret)) {
            throw new IllegalArgumentException("Access key secret should not be null or empty.");
        }

        this.accessKeyId = accessKeyId;
        this.accessKeySecret = accessKeySecret;
    }

    @Override
    public String getAccessKeyId() {
        return accessKeyId;
    }

    @Override
    public String getAccessKeySecret() {
        return accessKeySecret;
    }
}
