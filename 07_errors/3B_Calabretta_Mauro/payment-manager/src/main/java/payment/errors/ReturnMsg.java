package payment.errors;

import java.time.LocalDateTime;

public class ReturnMsg {
    private LocalDateTime timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public ReturnMsg(LocalDateTime timestamp, Integer status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public ReturnMsg(int value, String reasonPhrase, String reason, String requestURI) {
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public ReturnMsg setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public Integer getStatus() {
        return status;
    }

    public ReturnMsg setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getError() {
        return error;
    }

    public ReturnMsg setError(String error) {
        this.error = error;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ReturnMsg setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getPath() {
        return path;
    }

    public ReturnMsg setPath(String path) {
        this.path = path;
        return this;
    }
}
