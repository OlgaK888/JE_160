package je.dao;

import je.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends CrudRepository<Product, Long> {
}
