package payment.kafka;

import java.time.Instant;

public class KafkaHttpValue {
    private long timestamp;
    private String sourceIp;
    private String service;
    private String request;
    private String error;

    public KafkaHttpValue() {
        this.timestamp = Instant.now().getEpochSecond();
    }

    public KafkaHttpValue( String sourceIp, String service, String request, String error) {
        this.timestamp = Instant.now().getEpochSecond();
        this.sourceIp = sourceIp;
        this.service = service;
        this.request = request;
        this.error = error;
    }



    public long getTimestamp() {
        return timestamp;
    }

    public KafkaHttpValue setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    public String getSourceIp() {
        return sourceIp;
    }

    public KafkaHttpValue setSourceIp(String sourceIp) {
        this.sourceIp = sourceIp;
        return this;
    }

    public String getService() {
        return service;
    }

    public KafkaHttpValue setService(String service) {
        this.service = service;
        return this;
    }

    public String getRequest() {
        return request;
    }

    public KafkaHttpValue setRequest(String request) {
        this.request = request;
        return this;
    }

    public String getError() {
        return error;
    }

    public KafkaHttpValue setError(String error) {
        this.error = error;
        return this;
    }
}
