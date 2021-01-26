package payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import payment.model.Payment;
import payment.repository.PaymentRepository;

import java.util.Optional;

@Service
@Transactional
public class PaymentService implements ServiceInterface {
    @Autowired
    PaymentRepository orderRepository;

    @Override
    public Iterable<Payment> findAll() { return orderRepository.findAll(); }

    @Override
    public Payment save(Payment payment) { return orderRepository.save(payment); }


    @Override
    public Optional<Payment> findById(String orderId) { return orderRepository.findById(orderId); }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public void removeOrder(String orderId) { orderRepository.deleteById(orderId);}

//    @Override
//    public Iterable<Order> getOrderByDate(long startTmsp, long endTmsp) {
//        return  orderRepository.findCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(startTmsp, endTmsp);
//    }


}
