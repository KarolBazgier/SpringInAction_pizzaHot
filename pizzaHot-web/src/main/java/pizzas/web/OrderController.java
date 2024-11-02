package pizzas.web;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pizzas.Order;
import pizzas.Pizza;
import pizzas.User;
import pizzas.Ingredient;
import pizzas.data.OrderRepository;
import pizzas.data.PizzaRepository;
import pizzas.data.UserRepository;


import javax.validation.Valid;

import java.security.Principal;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
@ConfigurationProperties(prefix= "pizza.orders")
public class OrderController {
    @Setter
    private int pageSize = 20;
    private final OrderRepository orderRepo;
    private final PizzaRepository pizzaRepo;
    private final UserRepository userRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo, PizzaRepository pizzaRepo, UserRepository userRepo) {
        this.orderRepo = orderRepo;
        this.pizzaRepo = pizzaRepo;
        this.userRepo = userRepo;
    }




    @GetMapping("/current")
    public String orderForm(Model model,
                            @RequestParam(value = "pizzaId", required = true) Long pizzaId,
                            @AuthenticationPrincipal User user){
        Order order = new Order();
//        Pizza design = pizzaRepo.findById(pizzaId).orElseThrow();
        Pizza  design = pizzaRepo.findById(pizzaId).orElseThrow();
        order.addDesign(design);

        if (user != null) {
            order.setName(user.getFullName());
            order.setStreet(user.getStreet());
            order.setCity(user.getCity());
            order.setZip(user.getZip());
        }

        log.info(order.toString());
        model.addAttribute("order", order);

        return "orderForm";
    }
    @PostMapping()
    public String processOrder(@Valid @ModelAttribute("order") Order order,
                               Errors errors, SessionStatus session,
                               @AuthenticationPrincipal User user)   {
        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors().toString());
            return "orderForm";
        }

        order.setUser(user);

        orderRepo.save(order);
        session.setComplete();
        log.info("Zamówienie zostało złożone : " + order);
        return "redirect:/";
    }

    @GetMapping
    public String ordersForUser(@AuthenticationPrincipal User user, Model model){
        Pageable pageable = (Pageable) PageRequest.of(0, pageSize);
        model.addAttribute("orders", orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));

        return "orderList";
    }
}
