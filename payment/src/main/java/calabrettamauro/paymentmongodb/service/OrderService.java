package calabrettamauro.paymentmongodb.service;

import calabrettamauro.paymentmongodb.model.Order;

import java.util.Optional;

public interface OrderService {

    public Iterable<Order> findAll();

    public Order save (Order order);

    public Optional<Order> findByID(String orderId );

    public void removeOrder(String orderId);


}
