package pizzas.messaging;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;
import pizzas.Order;
@Component
public class JmsOrdeMessagingService implements OrderMessagingService {
    private JmsTemplate jms;

    @Autowired
    public JmsOrdeMessagingService(JmsTemplate jms) {
        this.jms = jms;
    }

    public void sendOrder(Order order) {
        jms.convertAndSend("pizzaHot.order.queue", order, message -> {
            message.setStringProperty("_typeId", "order");
            return message;
        });
    }

    private Message addOrderSource(Message message) throws JMSException {
        message.setStringProperty("X_ORDER_SOURCE", "WEB");
        return message;
    }
}
