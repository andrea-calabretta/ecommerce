package payment.kafka;

public class KafkaHttpValue extends KafkaValue{
    private String sourceIp;
    private String service;
    private String request;
    private String error;

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
