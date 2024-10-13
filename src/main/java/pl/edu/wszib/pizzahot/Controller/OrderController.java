package pl.edu.wszib.pizzahot.Controller;

import jakarta.websocket.Session;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.edu.wszib.pizzahot.Model.Order;
import pl.edu.wszib.pizzahot.Model.Pizza;
import pl.edu.wszib.pizzahot.Repository.OrderRepository;
import pl.edu.wszib.pizzahot.Repository.PizzaRepository;
import pl.edu.wszib.pizzahot.Repository.UserRepository;
import pl.edu.wszib.pizzahot.Security.User;

import javax.validation.Valid;
import java.security.Principal;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

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
        Pizza design = pizzaRepo.findById(pizzaId).orElseThrow();
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
}
