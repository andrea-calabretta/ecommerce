package payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import payment.model.Payment;
import payment.repository.PaymentRepository;

import java.util.ArrayList;
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
    public void removePayment(String orderId) { orderRepository.deleteById(orderId);}

    @Override
    public ArrayList<Payment> getPaymentByDate(long startTmsp, long endTmsp) {
        ArrayList<Payment> payments_tot= (ArrayList<Payment>) findAll();
        ArrayList<Payment> payments_filtered = new ArrayList<>();
        for (int i = 0; i< payments_tot.size(); i++)
        {
            if (payments_tot.get(i).getUnix_creation_ts() >= startTmsp &&
                    payments_tot.get(i).getUnix_creation_ts() <= endTmsp)
            {
                payments_filtered.add(payments_tot.get(i));
            }
        }
        return payments_filtered;
    }


}
