package by.ita.je.dao;

import by.ita.je.model.Category;
import org.hibernate.mapping.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDAO extends CrudRepository<Category, Long> {

    Category findByName(String name);
}
