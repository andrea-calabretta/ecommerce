package calabretta_mauro.payment_MongoDB.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Order {

    //@Id
    //private ObjectId _id;

    @Id
    private String orderId;
    private String userId;
    private String amountPaid; //Usiamo stringa per fare in modo che sia possibile inserire anche la valuta (€,$ ecc.)
    private String extraArgs;

    @JsonCreator
    public Order(String orderId, String userId, String amountPaid) {
        this.orderId = orderId;
        this.userId = userId;
        this.amountPaid = amountPaid;
    }

    public Order(String orderId, String userId, String amountPaid, String extraArgs) {
        this.orderId = orderId;
        this.userId = userId;
        this.amountPaid = amountPaid;
        this.extraArgs = extraArgs;
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

    public String getAmountPaid() {
        return amountPaid;
    }

    public Order setAmountPaid(String amountPaid) {
        this.amountPaid = amountPaid;
        return this;
    }

    public String getExtraArgs() {
        return extraArgs;
    }

    public Order setExtraArgs(String extraArgs) {
        this.extraArgs = extraArgs;
        return this;
    }

    @Override
    public String toString() {
        return "Order{" +
               // "_id=" + _id +
                ", orderId='" + orderId + '\'' +
                ", userId='" + userId + '\'' +
                ", amountPaid='" + amountPaid + '\'' +
                ", extraArgs='" + extraArgs + '\'' +
                '}';
    }
}
