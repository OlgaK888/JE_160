package by.ita.je.service;

import by.ita.je.dao.CategoryDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceImplTest {

    @Mock
    CategoryDAO categoryDAO;

    @InjectMocks
    CategoryServiceImpl categoryService;

    private Category categoryForTesting() {

        Category category = getCategory();
        category.setName("cupboard");
        return category;
    }

    private Category getCategory() {
        return new Category();
    }

    @Test
    void whenFindById_returnCategory() {

        Mockito.when(categoryDAO.findById(3L)).thenReturn(ofNullable(getCategory()));

        final Category actual =  categoryService.findById(3L);
        final Category expected = getCategory();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(categoryDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(categoryDAO.findById(100L)).thenReturn(empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  categoryService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Category with id = " + 100 + " is not found");
        Mockito.verify(categoryDAO, Mockito.times(1)).findById(100L);
    }

    /*@Test
    void whenFindByName_returnCategory() {

        Category categoryForTesting = categoryForTesting();
        Mockito.when(categoryDAO.findByName("cupboard")).thenReturn(Optional.of(categoryForTesting));

        final Category actual =  categoryService.findByName("cupboard");
        final Category expected = getCategory();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(categoryDAO, Mockito.times(1)).findByName("cupboard");
    }

    @Test
    void whenFindByName_throwNotFoundDataException() {

        Mockito.when(categoryDAO.findByName("car")).thenReturn(empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  categoryService.findByName("car"));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Category " + "car" + " is not found");
        Mockito.verify(categoryDAO, Mockito.times(1)).findByName("car");
    }*/

    @Test
    void whenFindAll_returnCategories() {

        final ArrayList<Category> givenCategories = new ArrayList<>();
        givenCategories.add(getCategory());
        givenCategories.add(getCategory());
        givenCategories.add(getCategory());
        givenCategories.add(getCategory());

        Mockito.when(categoryDAO.findAll()).thenReturn(givenCategories);

        final Collection<Category> actual = categoryService.findAllCategories();
        final Collection<Category> expected = new ArrayList<>();
        expected.add(getCategory());
        expected.add(getCategory());
        expected.add(getCategory());
        expected.add(getCategory());

        Assertions.assertEquals(expected, actual);
        Mockito.verify(categoryDAO,Mockito.times(1)).findAll();
    }

    @Test
    void whenFindAll_emptyList() {

        final Collection<Category> actual =  categoryService.findAllCategories();
        final Collection<Object> expected = Collections.emptyList();
        Assertions.assertEquals(expected, actual);
    }

}

