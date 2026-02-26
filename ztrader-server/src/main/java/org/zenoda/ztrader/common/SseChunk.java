package org.zenoda.ztrader.common;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class SseChunk {
    private String type;
    private Object content;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public static class Builder {
        private SseChunk sseChunk;

        public Builder() {
            sseChunk = new SseChunk();
        }

        public Builder type(String type) {
            sseChunk.setType(type);
            return this;
        }

        public Builder content(Object content) {
            sseChunk.setContent(content);
            return this;
        }

        public SseChunk build() {
            return sseChunk;
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
