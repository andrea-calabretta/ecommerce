package payment.kafka;

public class KafkaMsg {
    private String key;
    private KafkaValue value;

    public String getKey() {
        return key;
    }

    public KafkaMsg setKey(String key) {
        this.key = key;
        return this;
    }

    public KafkaValue getValue() {
        return value;
    }

    public KafkaMsg setValue(KafkaValue value) {
        this.value = value;
        return this;
    }
}
