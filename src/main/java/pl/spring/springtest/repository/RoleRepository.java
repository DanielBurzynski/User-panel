package pl.spring.springtest.repository;

import org.springframework.data.repository.CrudRepository;
import pl.spring.springtest.entity.Role;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long> {



    Optional<Role> findByName(String name);






}
