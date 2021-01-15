package controller;

import data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping (path = "/transactions")
public class TransactionController {

    @Autowired
    OrderRepository orderRepository;

    @GetMapping(path = "/id/{id}")
    public @ResponseBody
    Optional <Order> getId(@PathVariable String id){
        return orderRepository.findById(id);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<Order> getAll(){
        return orderRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public @ResponseBody String delete (@PathVariable String id){
        orderRepository.deleteById(id);
        return "The order " + id + " has been deleted!";
    }
}
