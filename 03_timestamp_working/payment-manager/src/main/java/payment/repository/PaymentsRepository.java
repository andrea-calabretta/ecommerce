package payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import payment.model.Payments;

public interface PaymentsRepository extends MongoRepository <Payments, String> {

    //public Iterable<Payments> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(long startTmsp, long endTmsp);
}
