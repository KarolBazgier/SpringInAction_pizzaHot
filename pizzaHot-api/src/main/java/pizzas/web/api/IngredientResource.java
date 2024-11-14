package pizzas.web.api;
import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import pizzas.Ingredient.Type;
import pizzas.Ingredient;
public class IngredientResource extends RepresentationModel<IngredientResource> {
    @Getter
    private String name;

    @Getter
    private Type type;

    public IngredientResource(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
