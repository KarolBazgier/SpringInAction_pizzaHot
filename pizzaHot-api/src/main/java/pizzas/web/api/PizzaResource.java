package pizzas.web.api;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import pizzas.Pizza;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class PizzaResource extends RepresentationModel<PizzaResource> {
    private static final IngredientResourceAssembler
            ingredientAssembler = new IngredientResourceAssembler();

    @Getter
    private final String name;

    @Getter
    private final Date createdAt;

    @Getter
    private final List<IngredientResource> ingredients;

    public PizzaResource(Pizza pizza) {
        this.name = pizza.getName();
        this.createdAt = pizza.getCreatedAt();
        this.ingredients = pizza.getIngredients().stream()
                .map(ingredientAssembler::toModel)
                .collect(Collectors.toList());
    }
}
