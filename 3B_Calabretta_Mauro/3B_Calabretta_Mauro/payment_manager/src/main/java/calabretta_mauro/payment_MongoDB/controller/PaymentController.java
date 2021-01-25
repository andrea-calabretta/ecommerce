package calabretta_mauro.payment_MongoDB.controller;


import calabretta_mauro.payment_MongoDB.kafka.PaymentsUpdateRequest;
import calabretta_mauro.payment_MongoDB.model.Payment;
import calabretta_mauro.payment_MongoDB.service.PaymentService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@Component
@RequestMapping(path = "/payment")
public class PaymentController {

    @Autowired
    PaymentService svc;

    @Value("${kafkaTopic}")
    private String topic;

    @Autowired
    private KafkaTemplate<String, String> template;

    Date date = new Date();

    //metodo che richiede come input il
    //msg che vogliamo pubblicare sul topic kafka
    public void sendMessage(String msg){
        template.send(topic, msg);
    }

//    @PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
//    public @ResponseBody Order add(@RequestBody Order order){
//
//        return svc.save(order);
//    }

    @PostMapping(path = "/ipn", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Payment
    add(@RequestBody Payment payment){
        PaymentsUpdateRequest updateRequest = new PaymentsUpdateRequest()
                .setPaymentId(payment.getPaymentId())
                .setUserId(payment.getUserId())
                .setOrderId(payment.getOrderId())
                .setAmountPaid(payment.getAmountPaid())
                .setCreationTstp(new Timestamp(date.getTime()));
        sendMessage(new Gson().toJson(updateRequest));
        return payment;
    }

    @GetMapping (path = "/transactions?fromTimestamp={beginTimestamp}&endTimestamp={endTimestamp}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Iterable<Payment> /*iterable per prenderli tutti in sequenza*/
    getPaymentsWithTimestamp(@PathVariable long beginTimestamp, @PathVariable long endTimestamp, @RequestHeader String xUserId){
        Timestamp begin = new Timestamp(beginTimestamp);
        Timestamp end = new Timestamp(endTimestamp);
        return svc.getPaymentsWithTimestamp(xUserId, begin ,end);
    }

}
