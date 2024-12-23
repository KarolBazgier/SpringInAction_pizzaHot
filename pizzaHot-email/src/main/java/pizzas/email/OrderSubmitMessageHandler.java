package pizzas.email;

import org.springframework.integration.core.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class OrderSubmitMessageHandler implements GenericHandler<Order> {
    private RestTemplate rest;
    private ApiProperties apiProps;


    public OrderSubmitMessageHandler(RestTemplate rest, ApiProperties apiProps) {
        this.rest = rest;
        this.apiProps = apiProps;
    }

    @Override
    public Object handle(Order payload, MessageHeaders headers) {
        rest.postForObject(apiProps.getUrl(), payload, String.class);
        return null;
    }
}
