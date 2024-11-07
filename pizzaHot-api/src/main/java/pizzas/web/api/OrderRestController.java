package pizzas.web.api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzas.Order;
import pizzas.data.OrderRepository;
import pizzas.messaging.JmsOrdeMessagingService;
import pizzas.messaging.OrderMessagingService;


import java.util.Optional;

@Slf4j
@RestController
@RequestMapping(value = "/rest/order", produces = "application/json")
@CrossOrigin(origins="*")
public class OrderRestController {

    private OrderRepository orderRepo;
    private JmsOrdeMessagingService orderMessagingService;

    public OrderRestController(OrderRepository orderRepo, JmsOrdeMessagingService orderMessagingService) {
        this.orderRepo = orderRepo;
        this.orderMessagingService = orderMessagingService;
    }

    @GetMapping(produces="application/json")
    public Iterable<Order> allOrders() {
        return orderRepo.findAll();
    }

    @PostMapping(consumes="application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order) {
        log.warn(order.toString());
        orderMessagingService.sendOrder(order);
        return orderRepo.save(order);
    }
    @PutMapping(path="/{orderId}", consumes="application/json")
    public ResponseEntity<Order> putOrder(@PathVariable Long orderId,  @RequestBody Order order) {
        Optional<Order> existingOrder = orderRepo.findById(orderId);

        if (existingOrder.isPresent()) {
            // Zaktualizowanie istniejącego zamówienia
            Order updatedOrder = existingOrder.get();
            updatedOrder.setName(order.getName());
            updatedOrder.setStreet(order.getStreet());
            updatedOrder.setCity(order.getCity());
            updatedOrder.setZip(order.getZip());
            updatedOrder.setCardNumber(order.getCardNumber());
            updatedOrder.setCardExpiration(order.getCardExpiration());
            updatedOrder.setCardCVV(order.getCardCVV());
            updatedOrder.setPizzas(order.getPizzas());

            orderRepo.save(updatedOrder);  // Zapis zaktualizowanego zamówienia
            return new ResponseEntity<>(updatedOrder, HttpStatus.OK);  // Zwracamy odpowiedź 200 OK
        } else {
            // Jeśli zamówienie nie istnieje, zwracamy błąd 404
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PatchMapping(path="/{orderId}", consumes="application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order patch) {

        Order order = orderRepo.findById(orderId).get();
        if (patch.getName() != null) {
            order.setName(patch.getName());
        }
        if (patch.getStreet() != null) {
            order.setStreet(patch.getStreet());
        }
        if (patch.getCity() != null) {
            order.setCity(patch.getCity());
        }
        if (patch.getZip() != null) {
            order.setZip(patch.getZip());
        }
        if (patch.getCardNumber() != null) {
            order.setCardNumber(patch.getCardNumber());
        }
        if (patch.getCardExpiration() != null) {
            order.setCardExpiration(patch.getCardExpiration());
        }
        if (patch.getCardCVV() != null) {
            order.setCardCVV(patch.getCardCVV());
        }
        return orderRepo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            orderRepo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
