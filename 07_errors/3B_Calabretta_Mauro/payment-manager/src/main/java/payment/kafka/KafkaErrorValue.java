package payment.kafka;

public class KafkaErrorValue extends KafkaValue{
    private String parameters;
    private String body;

    public String getParameters() {
        return parameters;
    }

    public KafkaErrorValue setParameters(String parameters) {
        this.parameters = parameters;
        return this;
    }

    public String getBody() {
        return body;
    }

    public KafkaErrorValue setBody(String body) {
        this.body = body;
        return this;
    }
}
