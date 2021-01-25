package payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import payment.model.Order;

import java.util.Optional;

public interface OrderRepository extends MongoRepository <Order, String> {


    //public Iterable<Order> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(long startTmsp, long endTmsp);
}
