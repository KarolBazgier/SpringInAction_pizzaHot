package pl.edu.wszib.pizzahot.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.pizzahot.Model.Ingredient;
import pl.edu.wszib.pizzahot.Model.Pizza;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

}
