package pl.spring.springtest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import pl.spring.springtest.entity.Product;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {


    public List<Product> findAll();

    public Product findAllById(Long id);

    public Page<Product> findAll(Pageable pageable);

}
