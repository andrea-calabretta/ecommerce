package payment.kafka;

public class KafkaMsg {
    private String key;
    private KafkaHttpValue value;

    public KafkaMsg(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public KafkaMsg setKey(String key) {
        this.key = key;
        return this;
    }


    public KafkaHttpValue getValue() {
        return value;
    }

    public KafkaMsg setValue(KafkaHttpValue value) {
        this.value = value;
        return this;
    }
}
