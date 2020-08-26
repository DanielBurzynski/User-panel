package pl.spring.springtest.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pl.spring.springtest.entity.Product;

import java.util.List;

public interface ServiceProduct {


    public List<Product> findAll();

    public List<Product> findByProductId(String id);

    public Product findAllById(Long id);

    public Page<Product> findAll(Pageable pageable);


    Page<Product> listAll(int pageNum, String sortField, String sortDir);
}
