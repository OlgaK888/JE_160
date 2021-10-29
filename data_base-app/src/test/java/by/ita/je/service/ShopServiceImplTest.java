package by.ita.je.service;

import by.ita.je.dao.ShopDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Shop;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ShopServiceImplTest {

    @Mock
    ShopDAO shopDAO;

    @InjectMocks
    ShopServiceImpl shopService;

    private Shop shopForTesting() {

        Shop shop = getShop();
        shop.setName("Test Calypso");
        shop.setCity("Borisov");
        shop.setAddress("Bolshaya str, 33");
        shop.setPhoneNumber(375293884851L);
        shop.setWorkingHours("10.00 - 18.00");
        return shop;
    }

    private Shop getShop() {
        return new Shop();
    }

    @Test
    void whenFindById_returnShop() {

        Mockito.when(shopDAO.findById(3L)).thenReturn(Optional.ofNullable(getShop()));

        final Shop actual =  shopService.findById(3L);
        final Shop expected = getShop();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(shopDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(shopDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  shopService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Shop with id = " + 100 + " is not found");
        Mockito.verify(shopDAO, Mockito.times(1)).findById(100L);
    }

    @Test
    void whenFindAll_returnShops() {

        final ArrayList<Shop> givenShops = new ArrayList<>();
        givenShops.add(getShop());
        givenShops.add(getShop());
        givenShops.add(getShop());
        givenShops.add(getShop());

        Mockito.when(shopDAO.findAll()).thenReturn(givenShops);

        final Collection<Shop> actual = shopService.findAllShops();
        final Collection<Shop> expected = new ArrayList<>();
        expected.add(getShop());
        expected.add(getShop());
        expected.add(getShop());
        expected.add(getShop());

        Assertions.assertEquals(expected, actual);
        Mockito.verify(shopDAO,Mockito.times(1)).findAll();
    }

    @Test
    void whenFindAll_emptyList() {

        final Collection<Shop> actual =  shopService.findAllShops();
        final Collection<Object> expected = Collections.emptyList();
        Assertions.assertEquals(expected, actual);
    }

}
