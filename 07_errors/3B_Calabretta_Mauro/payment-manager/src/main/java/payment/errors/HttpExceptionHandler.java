package payment.errors;

import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;


/*
The @ControllerAdvice annotation  allows you to handle exceptions across the whole application,
 not just to an individual controller.
 You can think of it as an interceptor of exceptions thrown by any methods annotated with:
  @RequestMapping, @GetMapping, @PostMapping, @PutMapping and so on.
*/
@ControllerAdvice
public class HttpExceptionHandler {
    @Autowired
    private  KafkaTemplate <String, String> kafkaTemplate;

    @Value("${kafkaError}")
    private String topicErrors;



}
