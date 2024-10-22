package pl.edu.wszib.pizzahot.Controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.wszib.pizzahot.Model.Ingredient;
import pl.edu.wszib.pizzahot.Model.Pizza;
import pl.edu.wszib.pizzahot.Repository.IngredientRepository;
import pl.edu.wszib.pizzahot.Repository.PizzaRepository;

import java.util.Optional;

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
    public Iterable<Pizza> recentPizzas(){
        PageRequest pageRequest = PageRequest.of(0,12,
                                        Sort.by("createdAt").descending());

        return pizzaRepo.findAll(pageRequest).getContent();
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