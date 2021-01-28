package payment.kafka;

public class KafkaHttpMsg {
    private String key;
    private KafkaHttpValue value;

    public KafkaHttpMsg(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public KafkaHttpMsg setKey(String key) {
        this.key = key;
        return this;
    }


    public KafkaHttpValue getValue() {
        return value;
    }

    public KafkaHttpMsg setValue(KafkaHttpValue value) {
        this.value = value;
        return this;
    }
}
