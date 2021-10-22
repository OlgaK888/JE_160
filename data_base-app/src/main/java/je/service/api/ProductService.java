package je.service.api;

import je.model.Product;
import je.model.Shop;

import java.util.Collection;

public interface ProductService {

    Product findById(Long id);

    Collection<Product> findAllProducts();
}
