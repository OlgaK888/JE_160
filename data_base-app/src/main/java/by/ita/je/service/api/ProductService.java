package by.ita.je.service.api;

import by.ita.je.model.Product;

import java.util.Collection;

public interface ProductService {

    Product findById(Long id);

    Collection<Product> findAllProducts();
}
