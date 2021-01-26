package payment.service;

import payment.model.Payment;

import java.util.Optional;

public interface ServiceInterface {
    public Iterable<Payment> findAll();

    public Payment save (Payment payment);

    public Optional<Payment> findById(String orderId);

    public long count();

    public void removeOrder(String orderId);

//    public Iterable<Order> getOrderByDate(long startTmsp, long endTmsp);
}
