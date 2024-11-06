package pizzas.kitchen.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;

import org.springframework.stereotype.Component;
import pizzas.Order;
import pizzas.kitchen.KitchenUI;

@Component
public class OrderListener {
    private KitchenUI kitchenUI;


    @Autowired
    public OrderListener(KitchenUI kitchenUI) {
        this.kitchenUI = kitchenUI;
    }

    @JmsListener(destination = "pizzaHot.order.queue")
    public void receiveOrder(Order order){
        kitchenUI.displayOrder(order);
    }
}
