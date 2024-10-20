//ok
package ru.simeon.NauJava.repositories;
import org.springframework.data.repository.CrudRepository;
import ru.simeon.NauJava.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
