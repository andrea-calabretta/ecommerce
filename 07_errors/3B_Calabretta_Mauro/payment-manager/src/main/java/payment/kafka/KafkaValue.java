package payment.kafka;

public class KafkaValue {
    private long ts; //timestamp

    public long getTs() {
        return ts;
    }

    public KafkaValue setTs(long ts) {
        this.ts = ts;
        return this;
    }
}
