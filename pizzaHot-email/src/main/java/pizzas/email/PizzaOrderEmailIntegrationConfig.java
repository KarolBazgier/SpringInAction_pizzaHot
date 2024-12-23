package pizzas.email;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.mail.dsl.Mail;
@Configuration
public class PizzaOrderEmailIntegrationConfig {
    @Bean
    public IntegrationFlow pizzaOrderEmailFlow(EmailProperties emailProperties,
                                               EmailOrderTransformer emailOrderTransformer,
                                               OrderSubmitMessageHandler orderSubmitMessageHandler){

        return IntegrationFlow.from(Mail.imapInboundAdapter(emailProperties.getImapUrl()),
                                e -> e.poller(
                                        Pollers.fixedDelay(emailProperties.getPollRate())))
                                .transform(emailOrderTransformer)
                                .handle(orderSubmitMessageHandler)
                                .get();
    }
}
