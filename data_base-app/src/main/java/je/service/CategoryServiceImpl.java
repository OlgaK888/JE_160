package je.service;

import je.dao.CategoryDAO;
import je.exception.NotFoundDataException;
import je.model.Category;
import je.service.api.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
