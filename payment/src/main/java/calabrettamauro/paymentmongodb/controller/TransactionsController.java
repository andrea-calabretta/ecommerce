package calabrettamauro.paymentmongodb.controller;

import calabrettamauro.paymentmongodb.model.Order;
import calabrettamauro.paymentmongodb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(path="/transactions")
public class TransactionsController {

    @Autowired
    OrderRepository orderrepository;

    @GetMapping(path="/id/{id}")
    public @ResponseBody
    Optional<Order> getId(@PathVariable String id){
        return orderrepository.findById(id);
    }


    @GetMapping(path="/all")
    public @ResponseBody Iterable<Order> getAll(){
        return orderrepository.findAll();
    }

    @DeleteMapping(path="/{id}")
    public @ResponseBody String delete(@PathVariable String id){
        orderrepository.deleteById(id);
        return "the order " + id + " has been deleted";
    }

}
