package by.ita.je.service;

import by.ita.je.dao.ShoppingCartDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.ShoppingCart;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ShoppingCartServiceImplTest {

    @Mock
    ShoppingCartDAO shoppingCartDAO;

    @InjectMocks
    ShoppingCartServiceImpl shoppingCartService;

    private ShoppingCart shoppingCartForTesting() {

        ShoppingCart shoppingCart = getShoppingCart();
        return shoppingCart;
    }

    private ShoppingCart getShoppingCart() {
        return new ShoppingCart();
    }

    @Test
    void whenCreate_returnShoppingCart() {

        ShoppingCart shoppingCartForTesting = shoppingCartForTesting();
        Mockito.when(shoppingCartDAO.save(shoppingCartForTesting)).thenReturn(getShoppingCart());

        final ShoppingCart actual = shoppingCartService.create(shoppingCartForTesting);
        final ShoppingCart expected = getShoppingCart();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(shoppingCartDAO,Mockito.times(1)).save(shoppingCartForTesting);
    }

    /*@Test
    void whenCreate_throwNotCorrectDataException() {

        NotCorrectDataException notCorrectDataException = Assertions.assertThrows(NotCorrectDataException.class,
                ()-> shoppingCartService.create(getShoppingCart()));

        Assertions.assertEquals(notCorrectDataException.getMessage(),"Not correct data");
        Mockito.verify(shoppingCartDAO,Mockito.times(0)).save(getShoppingCart());
    }*/

    @Test
    void whenFindById_returnShoppingCart() {

        Mockito.when(shoppingCartDAO.findById(3L)).thenReturn(Optional.ofNullable(getShoppingCart()));

        final ShoppingCart actual =  shoppingCartService.findById(3L);
        final ShoppingCart expected = getShoppingCart();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(shoppingCartDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(shoppingCartDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  shoppingCartService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Shopping cart with id = " + 100 + " is not found");
        Mockito.verify(shoppingCartDAO, Mockito.times(1)).findById(100L);
    }

    @Test
    void whenUpdate_returnShoppingCart() {

        ShoppingCart shoppingCartForTesting = shoppingCartForTesting();
        Mockito.when(shoppingCartDAO.findById(1L)).thenReturn(Optional.ofNullable(shoppingCartForTesting));
        Mockito.when(shoppingCartDAO.save(shoppingCartForTesting)).thenReturn(getShoppingCart());

        final ShoppingCart actual =  shoppingCartService.update(1L, shoppingCartForTesting);
        final ShoppingCart expected = getShoppingCart();
        Assertions.assertEquals(expected, actual);

        Mockito.verify(shoppingCartDAO, Mockito.times(1)).findById(1L);
        Mockito.verify(shoppingCartDAO, Mockito.times(1)).save(shoppingCartForTesting);
    }

    @Test
    void whenUpdate_throwNotFoundDataException() {

        ShoppingCart researchProjectForTesting = shoppingCartForTesting();
        Mockito.when(shoppingCartDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  shoppingCartService.update(100L, shoppingCartForTesting()));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Shopping cart with id = " + 100 + " is not found");
        Mockito.verify(shoppingCartDAO, Mockito.times(1)).findById(100L);
    }

}
