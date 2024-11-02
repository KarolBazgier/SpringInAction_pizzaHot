package pizzas.data;
import org.springframework.data.repository.PagingAndSortingRepository;
import pizzas.Pizza;


import java.util.Optional;

public interface PizzaRepository  extends PagingAndSortingRepository<Pizza, Long> {


    Optional<Pizza> findById(Long id);

    Pizza save(Pizza pizza);
}
