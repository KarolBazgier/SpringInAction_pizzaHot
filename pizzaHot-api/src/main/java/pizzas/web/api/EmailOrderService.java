package pizzas.web.api;

import org.springframework.stereotype.Service;
import pizzas.Ingredient;
import pizzas.Order;
import pizzas.Pizza;
import pizzas.User;
import pizzas.data.IngredientRepository;
import pizzas.data.UserRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmailOrderService {
    private UserRepository userRepo;
    private IngredientRepository ingredientRepo;

    public EmailOrderService(UserRepository userRepo, IngredientRepository ingredientRepo) {
        this.userRepo = userRepo;
        this.ingredientRepo = ingredientRepo;
    }

    public Order convertEmailToOrder(EmailOrder emailOrder){
        User user = userRepo.findByUsername(emailOrder.getEmail());
        Order order = new Order();
        if (user == null){
            order.setUser(null);
            order.setCardNumber(emailOrder.getEmail());
            order.setCardCVV(emailOrder.getEmail());
            order.setCardExpiration(emailOrder.getEmail());
            order.setStreet(emailOrder.getEmail());
            order.setCity(emailOrder.getEmail());
            order.setZip(emailOrder.getEmail());
            order.setPlacedAt(new Date());
        }else {
            order.setUser(user);
            order.setCardNumber(emailOrder.getEmail());
            order.setCardCVV(emailOrder.getEmail());
            order.setCardExpiration(emailOrder.getEmail());
            order.setStreet(user.getStreet());
            order.setCity(user.getCity());
            order.setZip(user.getZip());
            order.setPlacedAt(new Date());
        }

        List<EmailOrder.EmailPizza> emailPizzas = emailOrder.getPizzas();
        for (EmailOrder.EmailPizza emailPizza : emailPizzas){
            Pizza pizza = new Pizza();
            pizza.setName(emailPizza.getName());
            List<String> ingredientsId = emailPizza.getIngredients();

            List<Ingredient> ingredients = new ArrayList<>();
            for (String ingredientId : ingredientsId){
                Optional<Ingredient> optionalIngredient = ingredientRepo.findById(ingredientId);
                if (optionalIngredient.isPresent()){
                    ingredients.add(optionalIngredient.get());
                }
            }
            pizza.setIngredients(ingredients);
            order.addDesign(pizza);
        }
        return order;
    }

}
