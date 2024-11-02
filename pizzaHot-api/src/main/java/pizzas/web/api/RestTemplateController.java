package pizzas.web.api;
import org.springframework.web.client.RestTemplate;
import pizzas.Ingredient;


public class RestTemplateController {

   private final RestTemplate restTemplate;

   public RestTemplateController(RestTemplate restTemplate) {
       this.restTemplate = restTemplate;
   }


    public Ingredient getIngredientById(String ingredientId){
        return restTemplate.getForObject("http://localhost:8080/ingredients/{id}", Ingredient.class, ingredientId);
    }
}
