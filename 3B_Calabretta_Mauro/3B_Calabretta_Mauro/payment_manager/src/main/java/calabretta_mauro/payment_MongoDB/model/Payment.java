package calabretta_mauro.payment_MongoDB.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@Document
public class Payment {

    @Id
    private String paymentId;

    private String userId;
    private String orderId;
    private String amountPaid;

    private Timestamp creationTstp;
    private Timestamp updateTstp;

    @JsonCreator
    public Payment(String paymentId, String userId, String orderId, String amountPaid, Timestamp creationTstp, Timestamp updateTstp) {
        this.amountPaid = amountPaid;
        this.paymentId = paymentId;
        this.userId = userId;
        this.orderId = orderId;
        this.creationTstp = creationTstp;
        this.updateTstp = updateTstp;
    }

    public Payment(){

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

    public Timestamp getCreationTstp() {
        return creationTstp;
    }

    public Payment setCreationTstp(Timestamp creationTstp) {
        this.creationTstp = creationTstp;
        return this;
    }

    public Timestamp getUpdateTstp() {
        return updateTstp;
    }

    public Payment setUpdateTstp(Timestamp updateTstp) {
        this.updateTstp = updateTstp;
        return this;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId='" + paymentId + '\'' +
                ", userId='" + userId + '\'' +
                ", orderId='" + orderId + '\'' +
                ", amountPaid='" + amountPaid + '\'' +
                ", creationTstp=" + creationTstp +
                ", updateTstp=" + updateTstp +
                '}';
    }
}
