package pl.edu.wszib.pizzahot.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import pl.edu.wszib.pizzahot.Model.Pizza;

import java.util.Optional;

public interface PizzaRepository  extends PagingAndSortingRepository<Pizza, Long> {


    Optional<Pizza> findById(Long id);

    Pizza save(Pizza pizza);
}
