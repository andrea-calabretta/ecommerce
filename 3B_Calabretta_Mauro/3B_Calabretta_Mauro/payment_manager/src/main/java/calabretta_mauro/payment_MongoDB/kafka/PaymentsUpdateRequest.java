package calabretta_mauro.payment_MongoDB.kafka;


import java.io.Serializable;
import java.sql.Timestamp;

public class PaymentsUpdateRequest implements Serializable {

    private String paymentId;
    private String userId;
    private String orderId;
    private String amountPaid;

    private Timestamp creationTstp;

    public String getPaymentId() {
        return paymentId;
    }

    public PaymentsUpdateRequest setPaymentId(String paymentId) {
        this.paymentId = paymentId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public PaymentsUpdateRequest setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getOrderId() {
        return orderId;
    }

    public PaymentsUpdateRequest setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getAmountPaid() {
        return amountPaid;
    }

    public PaymentsUpdateRequest setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    public Timestamp getCreationTstp() {
        return creationTstp;
    }

    public PaymentsUpdateRequest setCreationTstp(Timestamp creationTstp) {
        this.creationTstp = creationTstp;
        return this;
    }

    @Override
    public String toString() {
        return "PaymentsUpdateRequest{" +
                "paymentId='" + paymentId + '\'' +
                ", userId='" + userId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amountPaid='" + amountPaid + '\'' +
                ", creationTstp=" + creationTstp +
                '}';
    }
}
