package com.alibaba.maxgraph.status;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.MoreObjects;

public class StatusResult {
    private BulkStatus status;
    private Object data;
    private String message;

    public static StatusResult fromJsonResult(String jsonResult) {
        return JSONObject.parseObject(jsonResult, StatusResult.class);
    }

    public BulkStatus getStatus() {
        return status;
    }

    public void setStatus(BulkStatus status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("status", status)
                .add("data", data)
                .add("message", message)
                .toString();
    }
}
