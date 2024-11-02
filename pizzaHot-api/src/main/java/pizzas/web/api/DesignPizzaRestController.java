
package pizzas.web.api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pizzas.Ingredient;
import pizzas.Pizza;
import pizzas.data.IngredientRepository;
import pizzas.data.PizzaRepository;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping(value = "/rest/design", produces = "application/json")
@CrossOrigin(origins="*")
public class DesignPizzaRestController {

    private PizzaRepository pizzaRepo;
    private IngredientRepository ingredientRepo;

    @Autowired
    EntityLinks entityLinks;

    public DesignPizzaRestController(PizzaRepository pizzaRepo, IngredientRepository ingredientRepo) {
        this.pizzaRepo = pizzaRepo;
        this.ingredientRepo = ingredientRepo;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients(){
        return ingredientRepo.findAll();
    }
    @GetMapping("/recent")
    public CollectionModel<EntityModel<Pizza>> recentPizzas(){
        PageRequest pageRequest = PageRequest.of(0,12,
                                        Sort.by("createdAt").descending());

        List<Pizza> pizzas = pizzaRepo.findAll(pageRequest).getContent();

        List<EntityModel<Pizza>> pizzasResources = pizzas.stream()
                .map(pizza -> EntityModel.of(pizza,
                        WebMvcLinkBuilder.linkTo(
                                WebMvcLinkBuilder.methodOn(DesignPizzaRestController.class).pizzaById(pizza.getId())
                        ).withSelfRel()
                        ))
                .collect(Collectors.toList());

        return CollectionModel.of(pizzasResources,
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(DesignPizzaRestController.class).recentPizzas()
                ).withSelfRel());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pizza> pizzaById(@PathVariable("id") Long id){
        Optional<Pizza> pizza = pizzaRepo.findById(id);
        if (pizza.isPresent()){
            return new ResponseEntity<>(pizza.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Pizza postPizza(@RequestBody Pizza newPizza){
        newPizza.combineIngredients();
        return pizzaRepo.save(newPizza);
    }




}