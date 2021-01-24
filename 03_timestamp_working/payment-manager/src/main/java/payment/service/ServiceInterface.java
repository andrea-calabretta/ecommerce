package payment.service;

import payment.model.Order;

import java.util.Optional;

public interface ServiceInterface {
    public Iterable<Order> findAll();

    public  Order save (Order order);

    public Optional<Order> findById(String orderId);

    public void removeOrder(String orderId);

    public Iterable<Order> getOrderByDate(long startTmsp, long endTmsp);
}
