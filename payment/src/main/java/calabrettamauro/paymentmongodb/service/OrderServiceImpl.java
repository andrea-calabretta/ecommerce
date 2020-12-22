package calabrettamauro.paymentmongodb.service;

import calabrettamauro.paymentmongodb.model.Order;
import calabrettamauro.paymentmongodb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("v1")
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderrepository;

    @Override
    public Iterable<Order> findAll() {
        return orderrepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderrepository.save(order);
    }

    @Override
    public Optional<Order> findByID(String orderId) {
        return orderrepository.findById(orderId);
    }

    @Override
    public void removeOrder(String orderId) {
        orderrepository.deleteById(orderId);
    }
}
