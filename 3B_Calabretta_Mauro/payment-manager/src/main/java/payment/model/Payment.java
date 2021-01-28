package payment.model;


import com.fasterxml.jackson.annotation.JsonCreator;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Document
public class Payment {


    private String orderId;
    private Integer userId;
    private BigDecimal amountPaid;
    private long timestamp;

    @JsonCreator
    public Payment(String orderId, Integer userId, BigDecimal amountPaid, long timestamp) {
        this.orderId = orderId;
        this.userId = userId;
        this.amountPaid = amountPaid;
        this.timestamp = timestamp;
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

    public long getTimestamp() {
        return timestamp;
    }

    public Payment setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }
}