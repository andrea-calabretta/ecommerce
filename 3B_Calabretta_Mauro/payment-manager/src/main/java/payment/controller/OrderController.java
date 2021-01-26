package payment.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import payment.data.OrderUpdateRequest;
import payment.healthCheck.pingAckBody;
import payment.model.Order;
import payment.service.OrderService;

import java.sql.Timestamp;
import java.time.Instant;

@Controller
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    OrderService svc;


    @Value("${kafkaTopic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> template;

    public void sendMessage(String msg){
        template.send(topic, msg);
    }

    //http://localhost:8088/order/ipn
    @PostMapping(path = "/ipn", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Order add(@RequestBody Order order){
        OrderUpdateRequest updateRequest = (OrderUpdateRequest) new OrderUpdateRequest()
                .setOrderId(order.getOrderId())
                .setUserId(order.getUserId())
                .setAmountPaid(order.getAmountPaid())
                .setUnix_creation_ts(Instant.now().getEpochSecond());
        sendMessage(new Gson().toJson(updateRequest));
        return updateRequest;
    }

    //http://localhost:8088/order/save
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order save(@RequestBody Order order){
        order.setUnix_creation_ts(Instant.now().getEpochSecond());
        return svc.save(order);
    }

    @PostMapping(path = "/ping")
    public @ResponseBody
    pingAckBody ping(){
        pingAckBody resPing = new pingAckBody("up");
        // facciamo un check al db
        try {
            svc.count(); //query di prova che restitusce il numero di collections presenti nel DB
            resPing.setDbStatus("up");
        }catch (Exception e){
            System.out.println("MongoDB not reachable. " + "code :" + e.getMessage());
            resPing.setDbStatus("down");
        }
        return resPing;

    }

    @GetMapping(path = "/tra")
    public @ResponseBody long
    getOrderByDate()
    {
        return svc.count();

    }

    @GetMapping(path = "/transactions")
    public @ResponseBody Iterable<Order>
    getOrderByDate(@RequestParam long fromTimestamp, @RequestParam long endTimestamp, @RequestHeader Integer userId)
    {
        Timestamp from = new Timestamp(fromTimestamp);
        Timestamp end = new Timestamp(endTimestamp);

        return svc.findAll();

    }





}
