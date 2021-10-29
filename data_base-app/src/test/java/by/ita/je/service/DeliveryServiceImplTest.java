package by.ita.je.service;

import by.ita.je.dao.DeliveryDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Delivery;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class DeliveryServiceImplTest {

    @Mock
    DeliveryDAO deliveryDAO;

    @InjectMocks
    DeliveryServiceImpl deliveryService;

    private Delivery deliveryForTesting() {

        Delivery delivery = getDelivery();
        return delivery;
    }

    private Delivery getDelivery() {
        return new Delivery();
    }

    @Test
    void whenFindById_returnDelivery() {

        Mockito.when(deliveryDAO.findById(3L)).thenReturn(Optional.ofNullable(getDelivery()));

        final Delivery actual =  deliveryService.findById(3L);
        final Delivery expected = getDelivery();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(deliveryDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(deliveryDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  deliveryService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Delivery with id = " + 100 + " is not found");
        Mockito.verify(deliveryDAO, Mockito.times(1)).findById(100L);
    }

}
