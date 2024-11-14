package pizzas.web.api;

import org.springframework.hateoas.CollectionModel;

import java.util.List;

public class PizzaResources extends CollectionModel<PizzaResource> {
    public PizzaResources(List<PizzaResource> pizzaResources) {
        super(pizzaResources);
    }
}
