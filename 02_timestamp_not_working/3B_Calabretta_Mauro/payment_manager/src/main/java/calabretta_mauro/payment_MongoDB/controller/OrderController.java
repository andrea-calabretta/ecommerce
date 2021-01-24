package calabretta_mauro.payment_MongoDB.controller;

import calabretta_mauro.payment_MongoDB.data.OrderUpdateRequest;
import calabretta_mauro.payment_MongoDB.model.Order;
import calabretta_mauro.payment_MongoDB.service.OrderService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;

@Controller
@RequestMapping(path = "/order")
public class OrderController {

    @Autowired
    OrderService svc;

    @Value("${kafkaTopic}")
    private String topic;

    @Autowired
    private KafkaTemplate <String, String> template;

    public void sendMessage(String msg){
        template.send(topic, msg);
    }

    //http://localhost:8088/order/add
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order add(@RequestBody Order order){
        OrderUpdateRequest updateRequest = (OrderUpdateRequest) new OrderUpdateRequest()
                .setOrderId(order.getOrderId())
                .setUserId(order.getUserId())
                .setAmountPaid(order.getAmountPaid());
        sendMessage(new Gson().toJson(updateRequest));
        return order;
    }

    //http://localhost:8088/order/cip
    @PostMapping(path = "/cip", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order cip(@RequestBody Order order){
        OrderUpdateRequest updateRequest = (OrderUpdateRequest) new OrderUpdateRequest()
                .setOrderId(order.getOrderId())
                .setUserId(order.getUserId())
                .setAmountPaid(order.getAmountPaid());
        sendMessage(new Gson().toJson(updateRequest));
        return order;
    }

    //http://localhost:8088/order/save
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order save(@RequestBody Order order){
        return svc.save(order);
    }


    @PostMapping(path = "/ipn", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order ipn(@RequestBody Order order){
        return order;
    }


    @GetMapping(path = "/transactions/{fromTimestamp}/{toTimestamp}")
    public @ResponseBody Iterable<Order>
    getOrderByDate(@PathVariable long fromTimestamp,
                   @PathVariable long toTimestamp,
                   @RequestHeader("userid") Integer uid){
        Timestamp from = new Timestamp(fromTimestamp);
        Timestamp end = new Timestamp(toTimestamp);
        if(uid == 0){
            return svc.getOrderByDate(fromTimestamp, toTimestamp);
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }


}
