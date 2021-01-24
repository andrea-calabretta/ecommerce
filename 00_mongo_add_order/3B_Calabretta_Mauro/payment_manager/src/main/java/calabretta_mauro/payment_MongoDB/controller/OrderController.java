package calabretta_mauro.payment_MongoDB.controller;

import calabretta_mauro.payment_MongoDB.model.Order;
import calabretta_mauro.payment_MongoDB.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    OrderService svc;



    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order add(@RequestBody Order order){

        return svc.save(order);
    }

}
