package payment.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import payment.data.OrderUpdateRequest;
import payment.model.Order;
import payment.service.OrderService;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping(path = "/order")
public class OrderController {
    @Autowired
    OrderService svc;

    Date date= new Date();

    @Value("${kafkaTopic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> template;

    public void sendMessage(String msg){
        template.send(topic, msg);
    }

    //http://localhost:8088/order/add
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Order add(@RequestBody Order order){
        OrderUpdateRequest updateRequest = (OrderUpdateRequest) new OrderUpdateRequest()
                .setOrderId(order.getOrderId())
                .setUserId(order.getUserId())
                .setAmountPaid(order.getAmountPaid())
                .setCreationTstp(new Timestamp(date.getTime()));
        sendMessage(new Gson().toJson(updateRequest));
        return updateRequest;
    }

    //http://localhost:8088/order/save
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order save(@RequestBody Order order){
        return svc.save(order);
    }



}
