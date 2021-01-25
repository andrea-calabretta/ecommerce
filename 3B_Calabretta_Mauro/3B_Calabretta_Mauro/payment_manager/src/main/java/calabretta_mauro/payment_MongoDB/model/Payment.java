package calabretta_mauro.payment_MongoDB.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.Id;

public class Payment {

    @Id
    private String paymentId;

    private String userId;
    private String orderId;
    private String amountPaid;

    @CreationTimestamp
    private long timestamp;

    @JsonCreator
    public Payment() {
        this.amountPaid = amountPaid;
        this.paymentId = paymentId;
        this.userId = userId;
        this.orderId = orderId;
    }

    public Payment(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public Payment setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Payment setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public Payment setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public Payment setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public Payment setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", userId='" + userId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amountPaid='" + amountPaid + '\'' +
                ", timestamp='" + timestamp + '\'' +
                '}';
    }
}
