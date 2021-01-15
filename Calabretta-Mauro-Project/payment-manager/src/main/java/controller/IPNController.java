package controller;

import data.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.awt.*;

@Controller
@RequestMapping(path = "/ipn")
public class IPNController {
    @Autowired
    OrderRepository orderRepository;

    @PostMapping (path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    Order add (@RequestBody Order order){
        return orderRepository.save(order);
    }
}
