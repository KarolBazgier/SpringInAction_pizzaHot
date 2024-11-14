package pizzas.email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import org.apache.commons.text.similarity.LevenshteinDistance;
import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;
import jakarta.mail.Message;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class EmailOrderTransformer extends AbstractMailMessageTransformer<Order> {
    private static final String SUBJECT_KEYWORDS = "PIZZA ORDER";
    @Override
    protected AbstractIntegrationMessageBuilder<Order> doTransform(Message message) {
        Order pizzaOrder = processPayload(message);
        return MessageBuilder.withPayload(pizzaOrder);
    }

    private Order processPayload(Message message){
        try {
            String subject = message.getSubject();
            if (subject.toUpperCase().contains(SUBJECT_KEYWORDS)) {
                String email =
                        ((InternetAddress) message.getFrom()[0]).getAddress();
                String content = message.getContent().toString();
                return parseEmailToOrder(email, content);
            }
        } catch (MessagingException e) {
        } catch (IOException e) {}
        return null;
    }

    private Order parseEmailToOrder(String email, String content) {
        Order order = new Order(email);
        String[] lines = content.split("\\r?\\n");
        for (String line : lines) {
            if (line.trim().length() > 0 && line.contains(":")) {
                String[] lineSplit = line.split(":");
                String pizzaName = lineSplit[0].trim();
                String ingredients = lineSplit[1].trim();
                String[] ingredientsSplit = ingredients.split(",");
                List<String> ingredientCodes = new ArrayList<>();
                for (String ingredientName : ingredientsSplit) {
                    String code = lookupIngredientCode(ingredientName.trim());
                    if (code != null) {
                        ingredientCodes.add(code);
                    }
                }

                Pizza pizza = new Pizza(pizzaName);
                pizza.setIngredients(ingredientCodes);
                order.addPizza(pizza);
            }
        }
        return order;
    }
    private String lookupIngredientCode(String ingredientName) {
        for (Ingredient ingredient : ALL_INGREDIENTS) {
            String ucIngredientName = ingredientName.toUpperCase();
            if (LevenshteinDistance.getDefaultInstance().apply(ucIngredientName, ingredient.getName()) < 3 ||
                    ucIngredientName.contains(ingredient.getName()) ||
                    ingredient.getName().contains(ucIngredientName)) {
                return ingredient.getCode();
            }
        }
        return null;
    }
    private static Ingredient[] ALL_INGREDIENTS = new Ingredient[] {
            new Ingredient("GRB", "Na grubym"),
            new Ingredient("CNK", "Na cienkim"),
            new Ingredient("WOL", "Mielona wołowina"),
            new Ingredient("KUR", "Szarpany kurczak"),
            new Ingredient("SAL", "Salami"),
            new Ingredient("PAP", "Papryka słodka"),
            new Ingredient("JAL", "Papryka jalapeno"),
            new Ingredient("OGK", "Ogórek kiszony"),
            new Ingredient("PIE", "Pieczarki"),
            new Ingredient("CZO", "Czosnkowy"),
            new Ingredient("OST", "Ostry")
    };
}
