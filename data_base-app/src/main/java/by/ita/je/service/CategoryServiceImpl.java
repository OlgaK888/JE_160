package by.ita.je.service;

import by.ita.je.dao.CategoryDAO;
import by.ita.je.model.Category;
import by.ita.je.service.api.CategoryService;
import by.ita.je.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    @Override
    public Category findById(Long id) throws NotFoundDataException {
        final Category category = categoryDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Category with id = " + id + " is not found"));
        return category;
    }

    @Override
    public Category findByName(String name) throws NotFoundDataException {
        final Category category = categoryDAO.findByName(name)
                //.orElseThrow(() -> new NotFoundDataException("Category " + name + " is not found"))
                ;
        return category;
    }

    @Override
    public Collection<Category> findAllCategories() {
        final Spliterator<Category> result = categoryDAO.findAll().spliterator();
        return StreamSupport
                .stream(result,false)
                .collect(Collectors.toList());
    }
}
