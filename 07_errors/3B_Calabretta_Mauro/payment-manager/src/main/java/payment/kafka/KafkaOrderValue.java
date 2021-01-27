package payment.kafka;

import java.math.BigDecimal;

public class KafkaOrderValue {
    private String orderId;
    private Integer userId;
    private BigDecimal amountPaid;

    public KafkaOrderValue(String orderId, Integer userId, BigDecimal amountPaid) {
        this.orderId = orderId;
        this.userId = userId;
        this.amountPaid = amountPaid;
    }

    public String getOrderId() {
        return orderId;
    }

    public KafkaOrderValue setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public KafkaOrderValue setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public KafkaOrderValue setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }
}
