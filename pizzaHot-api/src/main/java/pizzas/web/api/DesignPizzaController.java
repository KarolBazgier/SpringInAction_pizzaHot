package pizzas.web.api;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import lombok.extern.slf4j.Slf4j;
import pizzas.Ingredient;
import pizzas.Pizza;
import pizzas.data.IngredientRepository;
import pizzas.data.PizzaRepository;


import javax.validation.Valid;


@Slf4j
@Controller
@RequestMapping(value = "/design")
@SessionAttributes("order")

public class DesignPizzaController {

    private final IngredientRepository ingredientRepo;

    private final PizzaRepository pizzaRepo;



    @Autowired
    public DesignPizzaController(IngredientRepository ingredientRepository, PizzaRepository pizzaRepo) {
        this.ingredientRepo = ingredientRepository;
        this.pizzaRepo = pizzaRepo;
    }

    @GetMapping()
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(ingredient -> ingredients.add(ingredient));

        Ingredient.Type[] types = Ingredient.Type.values();
        for(Ingredient.Type type : types){
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }


        model.addAttribute("pizza", new Pizza());

        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute Pizza pizza, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            log.info("haserrror");
            List<Ingredient> ingredients = new ArrayList<>();
            ingredientRepo.findAll().forEach(ingredient -> ingredients.add(ingredient));

            Ingredient.Type[] types = Ingredient.Type.values();
            for(Ingredient.Type type : types){
                model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
            }
            return "design";
        }

        log.info("Przed zapisaem do bazy: " + pizza);
        pizza.combineIngredients();
        pizzaRepo.save(pizza);
        log.info("Przetwarzanie zamówienia: " + pizza);
        return "redirect:/orders/current?pizzaId=" + pizza.getId();
    }
    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                          .filter(ingredient -> ingredient.getType().equals(type))
                          .collect(Collectors.toList());
    }
    
}
