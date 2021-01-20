package calabretta_mauro.payment_MongoDB.service;

import calabretta_mauro.payment_MongoDB.model.Order;
import calabretta_mauro.payment_MongoDB.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OrderService implements ServiceInterface{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Iterable<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> findById(String orderId) {
        return orderRepository.findById(orderId);
    }

    @Override
    public void removeOrder(String orderId) {
        orderRepository.deleteById(orderId);
    }
}
