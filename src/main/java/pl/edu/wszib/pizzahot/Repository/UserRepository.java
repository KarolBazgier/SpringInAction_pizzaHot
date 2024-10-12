package pl.edu.wszib.pizzahot.Repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.wszib.pizzahot.Security.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
