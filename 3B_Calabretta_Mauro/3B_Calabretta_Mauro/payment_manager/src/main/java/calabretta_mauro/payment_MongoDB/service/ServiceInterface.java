package calabretta_mauro.payment_MongoDB.service;

import calabretta_mauro.payment_MongoDB.model.Payment;

import java.util.Optional;

public interface ServiceInterface {

    public Iterable<Payment> findAll();

    public Payment save (Payment payment);

    public Optional<Payment> findById(String orderId);

    public void removeOrder(String orderId);

    public Iterable<Payment> getPaymentByDate(long startTmsp, long endTmsp);
}
