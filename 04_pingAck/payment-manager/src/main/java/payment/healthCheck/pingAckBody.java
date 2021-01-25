package payment.healthCheck;

import com.fasterxml.jackson.annotation.JsonCreator;

public class pingAckBody {
    private String serviceStatus; //"up"
    private String dbStatus; // "up | down"

    @JsonCreator
    public pingAckBody(String serviceStatus, String dbStatus) {
        this.serviceStatus = serviceStatus;
        this.dbStatus = dbStatus;
    }
    @JsonCreator
    public pingAckBody(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public pingAckBody(){

    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public pingAckBody setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
        return this;
    }

    public String getDbStatus() {
        return dbStatus;
    }

    public pingAckBody setDbStatus(String dbStatus) {
        this.dbStatus = dbStatus;
        return this;
    }

    @Override
    public String toString() {
        return "pingAckBody{" +
                "serviceStatus='" + serviceStatus + '\'' +
                ", dbStatus='" + dbStatus + '\'' +
                '}';
    }
}
