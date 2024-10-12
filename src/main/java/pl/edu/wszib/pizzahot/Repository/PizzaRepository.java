package pl.edu.wszib.pizzahot.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.pizzahot.Model.Pizza;

public interface PizzaRepository extends CrudRepository<Pizza, Long> {


}
