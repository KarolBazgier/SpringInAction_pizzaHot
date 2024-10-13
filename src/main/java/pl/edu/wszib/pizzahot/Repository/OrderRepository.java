package pl.edu.wszib.pizzahot.Repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.pizzahot.Model.Order;
import pl.edu.wszib.pizzahot.Security.User;


import java.util.List;

public interface OrderRepository extends CrudRepository<Order, Long> {
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
