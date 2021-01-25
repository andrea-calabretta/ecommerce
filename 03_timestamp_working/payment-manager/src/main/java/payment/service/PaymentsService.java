package payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import payment.model.Payments;
import payment.repository.PaymentsRepository;

import java.util.Optional;

@Service
@Transactional
public class PaymentsService implements ServiceInterface{
    @Autowired
    PaymentsRepository paymentsRepository;

    @Override
    public Iterable<Payments> findAll() { return paymentsRepository.findAll(); }

    @Override
    public Payments save(Payments payments) { return paymentsRepository.save(payments); }

    @Override
    public Optional<Payments> findById(String orderId) { return paymentsRepository.findById(orderId); }

    @Override
    public void removeOrder(String orderId) { paymentsRepository.deleteById(orderId);}

    @Override
    public Iterable<Payments> getOrderByDate(long startTmsp, long endTmsp) {
        return  paymentsRepository.findAll();
    }
}
