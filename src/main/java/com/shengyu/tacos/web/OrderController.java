package com.shengyu.tacos.web;

import com.shengyu.tacos.Order;
import com.shengyu.tacos.User;
import com.shengyu.tacos.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;

//end::baseClass[]
//tag::baseClass[]

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderRepository orderRepo;
    public OrderController(OrderRepository orderRepo){
        this.orderRepo = orderRepo;
    }
    //end::baseClass[]
//tag::orderForm[]
    @GetMapping("/current")
    public String orderForm() {

        return "orderForm";
    }
//end::orderForm[]

/*
//tag::handlePost[]
  @PostMapping
  public String processOrder(Order order) {
    log.info("Order submitted: " + order);
    return "redirect:/";
  }
//end::handlePost[]
*/

    //tag::handlePostWithValidation[]
    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "orderForm";
        }
        order.setUser(user);
        orderRepo.save(order);
        sessionStatus.setComplete();
        log.info("Order submitted: " + order);
        return "redirect:/";
    }
//end::handlePostWithValidation[]

//tag::baseClass[]

}
//end::baseClass[]
