package pizzas.email;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Order {
    private final String email;
    private List<Pizza> pizzas = new ArrayList<>();

    public void addPizza(Pizza pizza){
        this.pizzas.add(pizza);
    }

}
