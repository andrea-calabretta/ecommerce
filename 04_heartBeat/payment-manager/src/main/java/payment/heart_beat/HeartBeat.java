package payment.heart_beat;

public class HeartBeat {
    private String service;
    private String serviceStatus;
    private String dbStatus;

    public HeartBeat(String service, String serviceStatus, String dbStatus) {
        this.service = service;
        this.serviceStatus = serviceStatus;
        this.dbStatus = dbStatus;
    }

    public String getService() {
        return service;
    }

    public HeartBeat setService(String service) {
        this.service = service;
        return this;
    }

    public String getServiceStatus() {
        return serviceStatus;
    }

    public HeartBeat setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
        return this;
    }

    public String getDbStatus() {
        return dbStatus;
    }

    public HeartBeat setDbStatus(String dbStatus) {
        this.dbStatus = dbStatus;
        return this;
    }

    @Override
    public String toString() {
        return "HeartBeat{" +
                "service='" + service + '\'' +
                ", serviceStatus='" + serviceStatus + '\'' +
                ", dbStatus='" + dbStatus + '\'' +
                '}';
    }
}
