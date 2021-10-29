package by.ita.je.service;

import by.ita.je.dao.ProductDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Category;
import by.ita.je.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

    @Mock
    ProductDAO productDAO;

    @InjectMocks
    ProductServiceImpl productService;

    private Product productForTesting() {

        Product product = getProduct();
        product.setName("cupboard Alfa");
        product.setDescription("Classic cupboard");
        product.setPrice(BigDecimal.valueOf(2100));
        product.setRating(4.2);
        return product;
    }

    private Product getProduct() {
        return new Product();
    }

    @Test
    void whenFindById_returnProduct() {

        Mockito.when(productDAO.findById(3L)).thenReturn(Optional.ofNullable(getProduct()));

        final Product actual =  productService.findById(3L);
        final Product expected = getProduct();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(productDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(productDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  productService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Product with id = " + 100 + " is not found");
        Mockito.verify(productDAO, Mockito.times(1)).findById(100L);
    }

    @Test
    void whenFindAll_returnProducts() {

        final ArrayList<Product> givenProducts = new ArrayList<>();
        givenProducts.add(getProduct());
        givenProducts.add(getProduct());
        givenProducts.add(getProduct());
        givenProducts.add(getProduct());

        Mockito.when(productDAO.findAll()).thenReturn(givenProducts);

        final Collection<Product> actual = productService.findAllProducts();
        final Collection<Product> expected = new ArrayList<>();
        expected.add(getProduct());
        expected.add(getProduct());
        expected.add(getProduct());
        expected.add(getProduct());

        Assertions.assertEquals(expected, actual);
        Mockito.verify(productDAO,Mockito.times(1)).findAll();
    }

    @Test
    void whenFindAll_emptyList() {

        final Collection<Product> actual =  productService.findAllProducts();
        final Collection<Object> expected = Collections.emptyList();
        Assertions.assertEquals(expected, actual);
    }

}
