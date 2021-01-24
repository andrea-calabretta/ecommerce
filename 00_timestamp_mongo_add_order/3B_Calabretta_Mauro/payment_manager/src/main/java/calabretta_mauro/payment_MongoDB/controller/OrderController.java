package calabretta_mauro.payment_MongoDB.controller;

import calabretta_mauro.payment_MongoDB.model.Order;
import calabretta_mauro.payment_MongoDB.service.OrderService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    OrderService svc;
    Date date = new Date();


    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order add(@RequestBody Order order){

        Order o = new Order();
        o.setOrderId(order.getOrderId());
        o.setUserId(order.getUserId());
        o.setAmountPaid(order.getAmountPaid());

        //o.setCreationTstp(new Timestamp(date.getTime()));
        o.toString();
        return svc.save(o);
    }

    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order save (@RequestBody Order order){

        Order o = new Order(order.getOrderId(), order.getUserId(), order.getAmountPaid());
        //o.setOrderId(order.getOrderId());
        //o.setUserId(order.getUserId());
        //o.setAmountPaid(order.getAmountPaid());

        //o.setCreationTstp(new Timestamp(date.getTime()));
        o.toString();
        return svc.save(o);
    }

}
