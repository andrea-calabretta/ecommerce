package payment.errors;

import com.google.gson.Gson;
import com.mongodb.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import payment.kafka.KafkaHttpValue;
import payment.kafka.KafkaMsg;
import payment.service.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;


/*
The @ControllerAdvice annotation  allows you to handle exceptions across the whole application,
 not just to an individual controller.
 You can think of it as an interceptor of exceptions thrown by any methods annotated with:
  @RequestMapping, @GetMapping, @PostMapping, @PutMapping and so on.
*/
@ControllerAdvice
public class HttpExceptionHandler {
//    @Autowired
//    private  KafkaTemplate <String, String> kafkaTemplate;
//
    @Autowired
    PaymentService svc;


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> generateHttpErrorMessage(HttpServletRequest request, ResponseStatusException ex)
    {
        KafkaHttpValue value = new KafkaHttpValue();
        value.setTs(System.currentTimeMillis());
        value.setSourceIp(request.getRemoteAddr());
        value.setService("Payment_Service");
        value.setRequest(request.getRequestURI() + " " + request.getMethod());

        if(ex.getStatus().is5xxServerError()){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String stacktrace = sw.toString();
            value.setError(stacktrace);
        }
        if(ex.getStatus().is4xxClientError())
        {
            value.setError(String.valueOf(ex.getStatus().value()));
        }
        KafkaMsg msg = new KafkaMsg();
        msg.setValue(value);
        msg.setKey("http_error");
        svc.sendError((new Gson().toJson(msg)));
//        kafkaTemplate.send(topicErrors, (new Gson().toJson(msg)));
        return  new ResponseEntity<Object>(new ReturnMsg(ex.getStatus().value(),
                ex.getStatus().getReasonPhrase(), ex.getReason(), request.getRequestURI()),
                new HttpHeaders(), ex.getStatus());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView throwHttpNotFound(NoHandlerFoundException ex, HttpServletRequest request, HttpServletResponse response, @Nullable Object handler) throws IOException{
        KafkaHttpValue value = new KafkaHttpValue();
        value.setTs(System.currentTimeMillis());
        value.setSourceIp(request.getRemoteAddr());
        value.setService("Payment_Service");
        value.setRequest(request.getRequestURI() + " " + request.getMethod());
        value.setError("404");
        KafkaMsg msg = new KafkaMsg();
        msg.setValue(value);
        msg.setKey("http_error");
        svc.sendError((new Gson().toJson(msg)));
//        kafkaTemplate.send(topicErrors,(new Gson().toJson(msg)));
        response.sendError(HttpServletResponse.SC_NOT_FOUND);
        return new ModelAndView();
    }


}
