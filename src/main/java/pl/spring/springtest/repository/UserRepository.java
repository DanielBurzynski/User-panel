package pl.spring.springtest.repository;

import org.springframework.data.repository.CrudRepository;
import pl.spring.springtest.entity.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByConfirmationToken(String token);


}
