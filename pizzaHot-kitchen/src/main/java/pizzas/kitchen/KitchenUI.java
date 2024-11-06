package pizzas.kitchen;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import pizzas.Order;


@Component
@Slf4j
public class KitchenUI {

    @JmsListener(destination = "pizzaHot.order.queue")
    public void displayOrder(Order order) {
        // TODO: Beef this up to do more than just log the received taco.
        //       To display it in some sort of UI.
        log.info("RECEIVED ORDER:  " + order);
    }

}