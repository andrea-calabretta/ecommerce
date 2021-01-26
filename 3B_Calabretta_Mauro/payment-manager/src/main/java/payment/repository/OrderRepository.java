package payment.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import payment.model.Order;


public interface OrderRepository extends MongoRepository <Order, String> {

    //Iterable<Order> findCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(long startTmsp, long endTmsp);

}
