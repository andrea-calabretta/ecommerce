package payment.model;


import com.fasterxml.jackson.annotation.JsonCreator;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Document
public class Order {

    @Id
    private String orderId;
    private Integer userId;
    private BigDecimal amountPaid;
    private Timestamp creationTstp;
    private Timestamp updateTstp;

    @JsonCreator
    public Order(String orderId, Integer userId, BigDecimal amountPaid, Timestamp creationTstp, Timestamp updateTstp) {
        this.orderId = orderId;
        this.userId = userId;
        this.amountPaid = amountPaid;
        this.creationTstp = creationTstp;
        this.updateTstp = updateTstp;
    }

    public Order() {

    }

    public String getOrderId() {
        return orderId;
    }

    public Order setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Order setUserId(Integer userId) {
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

    public Timestamp getCreationTstp() {
        return creationTstp;
    }

    public Order setCreationTstp(Timestamp creationTstp) {
        this.creationTstp = creationTstp;
        return this;
    }

    public Timestamp getUpdateTstp() {
        return updateTstp;
    }

    public Order setUpdateTstp(Timestamp updateTstp) {
        this.updateTstp = updateTstp;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", amountPaid=" + amountPaid +
                ", creationTstp=" + creationTstp +
                ", updateTstp=" + updateTstp +
                '}';
    }
}