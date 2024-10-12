package pl.edu.wszib.pizzahot.Controller;

import jakarta.websocket.Session;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import pl.edu.wszib.pizzahot.Model.Order;
import pl.edu.wszib.pizzahot.Model.Pizza;
import pl.edu.wszib.pizzahot.Repository.OrderRepository;
import pl.edu.wszib.pizzahot.Repository.PizzaRepository;

import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private final OrderRepository orderRepo;
    private final PizzaRepository pizzaRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo, PizzaRepository pizzaRepo) {
        this.orderRepo = orderRepo;
        this.pizzaRepo = pizzaRepo;
    }




    @GetMapping("/current")
    public String orderForm(Model model,
                            @RequestParam(value = "pizzaId", required = true) Long pizzaId){
        Order order = new Order();
        Pizza design = pizzaRepo.findById(pizzaId).orElseThrow();
        order.addDesign(design);
        log.info(order.toString());
        model.addAttribute("order", order);

        return "orderForm";
    }
    @PostMapping()
    public String processOrder(@Valid @ModelAttribute("order") Order order,
                               Errors errors, SessionStatus session)   {
        if (errors.hasErrors()) {
            log.warn(errors.getAllErrors().toString());
            return "orderForm";
        }

        orderRepo.save(order);
        session.setComplete();
        log.info("Zamówienie zostało złożone : " + order);
        return "redirect:/";
    }
}
