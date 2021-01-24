package calabretta_mauro.payment_MongoDB.data;

import calabretta_mauro.payment_MongoDB.model.Order;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderUpdateRequest extends Order implements Serializable {
    public OrderUpdateRequest() {
        super();

    }


    /*
    private String orderId;
    private Integer userId;
    private BigDecimal amountPaid;

    public String getOrderId() {
        return orderId;
    }

    public OrderUpdateRequest setOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    public String getUserId() {
        return userId;
    }

    public OrderUpdateRequest setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public BigDecimal getAmountPaid() {
        return amountPaid;
    }

    public OrderUpdateRequest setAmountPaid(BigDecimal amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    @Override
    public String toString() {
        return "OrderUpdateRequest{" +
                "orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", amountPaid=" + amountPaid +
                '}';
    }
    */

}



