package pizzas.web.api;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import pizzas.Pizza;

public class PizzaResourceAssembler extends RepresentationModelAssemblerSupport<Pizza, PizzaResource> {
    public PizzaResourceAssembler() {
        super(DesignPizzaRestController.class, PizzaResource.class);
    }

    @Override
    public PizzaResource toModel(Pizza pizza) {
        return new PizzaResource(pizza);
    }

    public PizzaResource toResource(Pizza pizza) {
        return new PizzaResource(pizza);
    }
}
