package pizzas.web.api;

import org.springframework.data.rest.webmvc.RepresentationModelAssemblers;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import pizzas.Ingredient;

public class IngredientResourceAssembler
                    extends RepresentationModelAssemblerSupport<Ingredient, IngredientResource> {
    public IngredientResourceAssembler() {
        super(IngredientRestController.class, IngredientResource.class);
    }

    @Override
    public IngredientResource toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }

    public IngredientResource instantiateResource(Ingredient ingredient){
        return new IngredientResource(ingredient);
    }
}
