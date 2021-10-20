package je.dao;

import je.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends CrudRepository<Category, Long> {
}
