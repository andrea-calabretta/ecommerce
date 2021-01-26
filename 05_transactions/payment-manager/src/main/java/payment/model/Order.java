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
    private long unix_creation_ts;
    private long unix_update_ts;

    @JsonCreator
    public Order(String orderId, Integer userId, BigDecimal amountPaid, long unix_creation_ts, long unix_update_ts) {
        this.orderId = orderId;
        this.userId = userId;
        this.amountPaid = amountPaid;
        this.unix_creation_ts = unix_creation_ts;
        this.unix_update_ts = unix_update_ts;
    }

    public Order(){

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

    public long getUnix_creation_ts() {
        return unix_creation_ts;
    }

    public Order setUnix_creation_ts(long unix_creation_ts) {
        this.unix_creation_ts = unix_creation_ts;
        return this;
    }

    public long getUnix_update_ts() {
        return unix_update_ts;
    }

    public Order setUnix_update_ts(long unix_update_ts) {
        this.unix_update_ts = unix_update_ts;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", userId=" + userId +
                ", amountPaid=" + amountPaid +
                ", unix_start_ts=" + unix_creation_ts +
                ", unix_update_ts=" + unix_update_ts +
                '}';
    }
}