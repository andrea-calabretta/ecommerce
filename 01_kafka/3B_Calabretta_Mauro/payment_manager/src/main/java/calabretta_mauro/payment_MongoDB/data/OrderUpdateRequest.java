package calabretta_mauro.payment_MongoDB.data;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderUpdateRequest implements Serializable {
    private String orderId;
    private String userId;
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
}



