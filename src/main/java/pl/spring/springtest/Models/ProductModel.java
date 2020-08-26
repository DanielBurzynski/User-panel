package pl.spring.springtest.Models;

import pl.spring.springtest.entity.Product;

import java.util.List;

public class ProductModel {

    private List<Product> products;

    public Product find(Long id) {
        for (Product product : this.products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }
}
