package calabretta_mauro.payment_MongoDB.controller;

import calabretta_mauro.payment_MongoDB.data.OrderUpdateRequest;
import calabretta_mauro.payment_MongoDB.model.Order;
import calabretta_mauro.payment_MongoDB.service.OrderService;
import com.google.gson.Gson;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
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

    /*
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order add(@RequestBody Order order){
        return svc.save(order);
    }
    */
    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Order add(@RequestBody Order order){
        OrderUpdateRequest updateRequest = new OrderUpdateRequest()
                .setOrderId(order.getOrderId())
                .setUserId(order.getUserId())
                .setAmountPaid(order.getAmountPaid());
        sendMessage(new Gson().toJson(updateRequest));
        return order;
    }


}
