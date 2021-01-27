package payment.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Document
public class Payment {

    @Id
    private String orderId;
    private Integer userId;
    private BigDecimal amountPaid;
    private long unix_creation_ts;


    @JsonCreator
    public Payment(String orderId, Integer userId, BigDecimal amountPaid, long unix_creation_ts) {
        this.orderId = orderId;
        this.userId = userId;
        this.amountPaid = amountPaid;
        this.unix_creation_ts = unix_creation_ts;

    }

    public Payment(){

    }

    public String getOrderId() {
        return orderId;
    }

    public Payment setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public Integer getUserId() {
        return userId;
    }

    public Payment setUserId(Integer userId) {
        this.userId = userId;
        return this;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public Payment setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    public long getUnix_creation_ts() {
        return unix_creation_ts;
    }

    public Payment setUnix_creation_ts(long unix_creation_ts) {
        this.unix_creation_ts = unix_creation_ts;
        return this;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", amountPaid=" + amountPaid +
                ", unix_start_ts=" + unix_creation_ts +
                '}';
    }
}