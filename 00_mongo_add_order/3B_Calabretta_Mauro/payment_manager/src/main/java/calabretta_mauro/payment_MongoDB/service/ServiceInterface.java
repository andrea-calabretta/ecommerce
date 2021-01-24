package calabretta_mauro.payment_MongoDB.service;

import calabretta_mauro.payment_MongoDB.model.Order;

import java.util.Optional;

public interface ServiceInterface {

    public Iterable<Order> findAll();

    public  Order save (Order order);

    public Optional<Order> findById(String orderId);

    public void removeOrder(String orderId);
}