package calabretta_mauro.payment_MongoDB.service;

import calabretta_mauro.payment_MongoDB.model.Payment;
import calabretta_mauro.payment_MongoDB.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class PaymentService implements ServiceInterface{
    @Autowired
    PaymentRepository paymentRepository;

    @Override
    public Iterable<Payment> findAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> findById(String orderId) {
        return paymentRepository.findById(orderId);
    }

    @Override
    public void removeOrder(String orderId) {
        paymentRepository.deleteById(orderId);
    }

    @Override
    public Iterable<Payment> getPaymentByDate(long startTmsp, long endTmsp){
        return paymentRepository.findAll();
    }

//    public Iterable<Payment> getPaymentsWithTimestamp(String xUserId, Timestamp beginTimestamp, Timestamp endTimestamp){
//        if (xUserId == "0"){
//            return paymentRepository.findByUserIdAndTimestampGreaterThanEqualAndTimestampLessThanEqual(xUserId, beginTimestamp, endTimestamp);
//        }else {
//            //System.out.printf("\"Invalid userId, must be 0\"") ; //da sistemare con errors
//            return paymentRepository.findByUserIdAndTimestampGreaterThanEqualAndTimestampLessThanEqual(xUserId, beginTimestamp, endTimestamp);
//        }
//    }

    /*
    public Iterable<Payment> getPaymentByDate(Integer userId, Timestamp fromTimestamp, Timestamp endTimestamp){
        try{
            if (userId == null || userId == 0) {
                return repository.findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(fromTimestamp, endTimestamp);
            } else {
                return repository.findByUserIdAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(userId, fromTimestamp, endTimestamp);
            }
        }
        catch (Exception e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No payments found");
        }
    } */
}
