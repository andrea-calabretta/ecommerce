package calabretta_mauro.payment_MongoDB.repository;

import calabretta_mauro.payment_MongoDB.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.sql.Timestamp;

public interface OrderRepository extends MongoRepository <Order, String> {
    //ci permette di usufruire delle funzionalità di gestione del database Mongo, tra cui le operazioni CRUD

    public Iterable<Order> findByCreatedAtGreaterThanEqualAndCreatedAtLessThanEqual(long startTmstp, long endTmstp);

}
