
package payment.errors;

import java.time.Instant;

public class ResponseMsg {
    private long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ResponseMsg(Integer status, String error, String message, String path) {
        this.timestamp = Instant.now().getEpochSecond();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public ResponseMsg setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ResponseMsg setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getError() {
        return error;
    }

    public ResponseMsg setError(String error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseMsg setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ResponseMsg setPath(String path) {
        this.path = path;
        return this;
    }
}

