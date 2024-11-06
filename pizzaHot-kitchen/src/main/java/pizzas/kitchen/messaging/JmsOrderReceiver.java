package pizzas.kitchen.messaging;

import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.stereotype.Component;
import pizzas.Order;
import pizzas.kitchen.OrderReceiver;
@Component
public class JmsOrderReceiver implements OrderReceiver {
    private JmsTemplate jms;

    @Autowired
    public JmsOrderReceiver(JmsTemplate jms) {
        this.jms = jms;
    }

    @Override
    public Order receiveOrder() {
       return (Order) jms.receiveAndConvert("pizzaHot.order.queue");
    }
}
