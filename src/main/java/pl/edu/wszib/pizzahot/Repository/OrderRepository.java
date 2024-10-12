package pl.edu.wszib.pizzahot.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.pizzahot.Model.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {

}
