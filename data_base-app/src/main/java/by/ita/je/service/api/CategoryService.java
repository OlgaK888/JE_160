package by.ita.je.service.api;

import by.ita.je.model.Category;

import java.util.Collection;

public interface CategoryService {

    Category findById(Long id);

    //Category findByName(String name);

    Collection<Category> findAllCategories();

}
