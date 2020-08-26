package pl.spring.springtest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.spring.springtest.entity.Product;
import pl.spring.springtest.repository.ProductRepository;
import pl.spring.springtest.service.ServiceProduct;

import java.util.List;
@Service
public class ServiceProductImpl implements ServiceProduct {

    @Autowired
    ProductRepository productRepository;



    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByProductId(String id) {
        return null;
    }

    @Override
    public Product findAllById(Long id) {
        return productRepository.findAllById(id);
    }

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Page<Product> listAll(int pageNum, String sortField, String sortDir) {

            Pageable pageable = PageRequest.of(pageNum - 1, 5,
                    sortDir.equals("asc") ? Sort.by(sortField).ascending()
                            : Sort.by(sortField).descending()
            );

            return productRepository.findAll(pageable);
        }
    }

