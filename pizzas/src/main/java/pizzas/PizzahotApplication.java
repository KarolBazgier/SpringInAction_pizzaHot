package pizzas;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import pizzas.Pizza;


@SpringBootApplication


public class PizzahotApplication {

	public static void main(String[] args) {
		SpringApplication.run(PizzahotApplication.class, args);
//		RestTemplateController rest = new RestTemplateController(new RestTemplate());
//
//		Ingredient ingredient = rest.getIngredientById("GRB");
//		log.warn(ingredient.toString());

	}

}
