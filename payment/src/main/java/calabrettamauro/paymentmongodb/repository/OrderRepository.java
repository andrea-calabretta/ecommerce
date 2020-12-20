package calabrettamauro.paymentmongodb.repository;

import calabrettamauro.paymentmongodb.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {
    //ci permette di usufruire delle funzionalità di gestione del database Mongo, tra cui le operazioni CRUD
}
