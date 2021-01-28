package payment.controller;

import com.google.gson.Gson;
import org.relaxng.datatype.DatatypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import payment.kafka.KafkaPaymentUpdate;
import payment.healthCheck.pingAckBody;
import payment.model.Payment;
import payment.service.PaymentService;

import java.time.Instant;
import java.util.ArrayList;

@Controller
@RequestMapping(path = "/payment")
public class PaymentController {
    @Autowired
    PaymentService svc;

    //http://localhost:8088/payment/ping
    @PostMapping(path = "/ping")
    public @ResponseBody
    pingAckBody ping(){
        pingAckBody resPing = new pingAckBody("up");
        // let's check db status
        try {
            svc.count(); //simple query to count n° of collections in db, if the db is not reachable it will cause an exception (that's what we are really interested in)
            resPing.setDbStatus("up");
        }catch (Exception e){
            System.out.println("MongoDB not reachable. " + "code :" + e.getMessage());
            resPing.setDbStatus("down");
        }
        return resPing;
    }

    //http://localhost:8088/payment/ipn
    @PostMapping(path = "/ipn", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Payment add(@RequestBody Payment payment){

        try {
            //save on DB
            payment.getUnix_creation_ts(Instant.now().getEpochSecond());
            svc.save(payment);
            //save on topic orders in Kafka
            KafkaPaymentUpdate updateRequest = (KafkaPaymentUpdate) new KafkaPaymentUpdate()
                    .setOrderId(payment.getOrderId())
                    .setUserId(payment.getUserId())
                    .setAmountPaid(payment.getAmountPaid())
                    .setUnix_creation_ts(Instant.now().getEpochSecond());
            svc.sendMessage(new Gson().toJson(updateRequest));
            return updateRequest;
        }catch (Exception e ){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR) ;
        }
    }

    //http://localhost:8088/transactions?fromTimestamp=unixTimestamp1&endTimestamp=unixTimestamp2
    @GetMapping(path = "/transactions")
    public @ResponseBody
    ArrayList <Payment>
    getPaymentsByDate(@RequestParam long fromTimestamp,
                   @RequestParam long endTimestamp,
                   @RequestHeader Integer userId)
    {

        if (userId == 0) return svc.getTransactions(fromTimestamp, endTimestamp);
        else throw new ResponseStatusException(HttpStatus.UNAUTHORIZED) ;
    }

    //http://localhost:8088/payment/save
    @PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Payment save(@RequestBody Payment payment){
        payment.setUnix_creation_ts(Instant.now().getEpochSecond());
        return svc.save(payment);
    }


    //http://localhost:8088/payment/all
    @GetMapping(path = "/all")
    public @ResponseBody
    Iterable <Payment> getAllPayments() { return svc.findAll(); }


}
