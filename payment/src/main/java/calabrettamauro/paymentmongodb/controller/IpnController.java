package calabrettamauro.paymentmongodb.controller;

import calabrettamauro.paymentmongodb.model.Order;
import calabrettamauro.paymentmongodb.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/ipn")
public class IpnController {
    @Autowired
    OrderRepository orderrepository;

//    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public Order create(@RequestBody Order order) {
//        return orderrepository.save(order);
//    }

    @PostMapping(path="/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order add(@RequestBody Order order) {
        return orderrepository.save(order);

    }
}


