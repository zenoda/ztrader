package org.zenoda.ztrader.data.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class TuShareRequest {
    @JsonProperty("api_name")
    private String apiName;
    private String token;
    private Map<Object, Object> params;
    private String fields;

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Map<Object, Object> getParams() {
        return params;
    }

    public void setParams(Map<Object, Object> params) {
        this.params = params;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private TuShareRequest request;

        public Builder() {
            request = new TuShareRequest();
        }

        public Builder apiName(String apiName) {
            request.setApiName(apiName);
            return this;
        }

        public Builder token(String token) {
            request.setToken(token);
            return this;
        }

        public Builder params(Map<Object, Object> params) {
            request.setParams(params);
            return this;
        }

        public Builder fields(String fields) {
            request.setFields(fields);
            return this;
        }

        public TuShareRequest build() {
            return request;
        }
    }
}
