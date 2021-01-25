package calabretta_mauro.payment_MongoDB.repository;

import calabretta_mauro.payment_MongoDB.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;

public interface PaymentRepository extends MongoRepository <Payment, String> {
    //metodi per il timestamp
    public Iterable<Payment> findByUserIdAndTimestampGreaterThanEqualAndTimestampLessThanEqual(String userId, Date beginTimestamp, Date endTimestamp);

}
/*    public Iterable<Payment> findByUserIdAndCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(Integer userId, Date fromTimestamp, Date endTimestamp);
    public Iterable<Payment> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(Date fromTimestamp, Date endTimestamp);*/