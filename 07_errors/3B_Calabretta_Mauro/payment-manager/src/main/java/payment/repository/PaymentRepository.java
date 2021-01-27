package payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import payment.model.Payment;


public interface PaymentRepository extends MongoRepository <Payment, String> {

    //Iterable<Order> findCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(long startTmsp, long endTmsp);

}
