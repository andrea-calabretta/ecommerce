package calabretta_mauro.payment_MongoDB.kafka;


public class PaymentsUpdateRequest {

    private String paymentId;
    private String userId;
    private String orderId;
    private String amountPaid;

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

    @Override
    public String toString() {
        return "PaymentsUpdateRequest{" +
                "paymentId='" + paymentId + '\'' +
                ", userId='" + userId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amountPaid='" + amountPaid + '\'' +
                '}';
    }
}
