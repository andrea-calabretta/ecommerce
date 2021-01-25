package payment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import payment.model.Order;
import payment.repository.OrderRepository;

import java.util.Optional;

@Service
@Transactional
public class OrderService implements ServiceInterface{
    @Autowired
    OrderRepository orderRepository;

    @Override
    public Iterable<Order> findAll() { return orderRepository.findAll(); }

    @Override
    public Order save(Order order) { return orderRepository.save(order); }


    @Override
    public Optional<Order> findById(String orderId) { return orderRepository.findById(orderId); }

    @Override
    public long count() {
        return orderRepository.count();
    }

    @Override
    public void removeOrder(String orderId) { orderRepository.deleteById(orderId);}

    @Override
    public Iterable<Order> getOrderByDate(long startTmsp, long endTmsp) {
        return  orderRepository.findAll();
    }
}
