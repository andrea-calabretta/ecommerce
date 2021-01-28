package payment.errors;

import com.google.gson.Gson;
import com.mongodb.lang.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
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


/*
The @ControllerAdvice annotation  allows you to handle exceptions across the whole application,
 not just to an individual controller.
 You can think of it as an interceptor of exceptions thrown by any methods annotated with:
  @RequestMapping, @GetMapping, @PostMapping, @PutMapping and so on.
*/
@ControllerAdvice
public class HttpExceptionHandler {

    @Autowired
    PaymentService svc;

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> generateHttpErrorMessage(HttpServletRequest req, ResponseStatusException ex)
    {
        KafkaHttpValue httpVal = new KafkaHttpValue();
        httpVal.setSourceIp(req.getRemoteAddr());
        httpVal.setService("payments");
        httpVal.setRequest(req.getRequestURI() + " " + req.getMethod());

        if(ex.getStatus().is5xxServerError()){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            ex.printStackTrace(pw);
            String stacktrace = sw.toString();
            httpVal.setError(stacktrace);
        }
        if(ex.getStatus().is4xxClientError())
        {
            httpVal.setError(String.valueOf(ex.getStatus().value()));
        }
        KafkaMsg msg = new KafkaMsg("http_errors");
        msg.setValue(httpVal);
        svc.sendError((new Gson().toJson(msg)));
        return  new ResponseEntity<Object>(new ResponseMsg(ex.getStatus().value(),
                ex.getStatus().getReasonPhrase(), ex.getReason(), req.getRequestURI()),
                new HttpHeaders(), ex.getStatus());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView throwHttpNotFound(NoHandlerFoundException ex, HttpServletRequest req, HttpServletResponse res, @Nullable Object handler) throws IOException{
        KafkaHttpValue httpVal = new KafkaHttpValue();
        httpVal.setSourceIp(req.getRemoteAddr());
        httpVal.setService("payments");
        httpVal.setRequest(req.getRequestURI() + " " + req.getMethod());
        httpVal.setError("404");
        KafkaMsg msg = new KafkaMsg("http_errors");
        msg.setValue(httpVal);
        svc.sendError((new Gson().toJson(msg)));
        res.sendError(HttpServletResponse.SC_NOT_FOUND);
        return new ModelAndView();
    }


}
