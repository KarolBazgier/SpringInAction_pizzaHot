//package pizzas.messaging;
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.annotation.EnableJms;
//import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
//import pizzas.Order;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@Configuration
//@EnableJms
//public class MessagingConfig {
//
//    @Bean
//    public MappingJackson2MessageConverter messageConverter() {
//        MappingJackson2MessageConverter messageConverter =
//                new MappingJackson2MessageConverter();
//        messageConverter.setTypeIdPropertyName("_typeId");
//
//        Map<String, Class<?>> typeIdMappings = new HashMap<String, Class<?>>();
//        typeIdMappings.put("order", Order.class);
//        messageConverter.setTypeIdMappings(typeIdMappings);
//
//        return messageConverter;
//    }
//}
