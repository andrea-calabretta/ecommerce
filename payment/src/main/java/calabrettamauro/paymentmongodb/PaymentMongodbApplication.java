package calabrettamauro.paymentmongodb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PaymentMongodbApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentMongodbApplication.class, args);
    }

}
