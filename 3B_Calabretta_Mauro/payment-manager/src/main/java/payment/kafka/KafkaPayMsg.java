package payment.kafka;

public class KafkaPayMsg {
    private String key;
    private KafkaPaymentUpdate value;

    public KafkaPayMsg(String key) {
        this.key = key;
    }

    public KafkaPayMsg(String key, KafkaPaymentUpdate value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public KafkaPayMsg setKey(String key) {
        this.key = key;
        return this;
    }

    public KafkaPaymentUpdate getValue() {
        return value;
    }

    public KafkaPayMsg setValue(KafkaPaymentUpdate value) {
        this.value = value;
        return this;
    }
}
