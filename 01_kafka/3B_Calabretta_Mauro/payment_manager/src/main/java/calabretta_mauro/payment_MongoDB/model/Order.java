package calabretta_mauro.payment_MongoDB.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
public class Order {

    @Id
    private String orderId;
    private String userId;
    private BigDecimal amountPaid;

    @JsonCreator
    public Order(String orderId, String userId, BigDecimal amountPaid) {
        this.orderId = orderId;
        this.userId = userId;
        this.amountPaid = amountPaid;
    }


    public String getOrderId() {
        return orderId;
    }

    public Order setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public Order setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public Order setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }


    @Override
    public String toString() {
        return "Order{" +
                ", orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", amountPaid='" + amountPaid + '\'';
    }
}
