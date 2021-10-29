package by.ita.je.service;

import by.ita.je.dao.PurchaseOrderDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.PurchaseOrder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PurchaseOrderServiceImplTest {

    @Mock
    PurchaseOrderDAO purchaseOrderDAO;

    @InjectMocks
    PurchaseOrderServiceImpl purchaseOrderService;

    private PurchaseOrder purchaseOrderForTesting() {

        PurchaseOrder purchaseOrder = getPurchaseOrder();
        purchaseOrder.setSum(BigDecimal.valueOf(4300));
        return purchaseOrder;
    }

    private PurchaseOrder getPurchaseOrder() {
        return new PurchaseOrder();
    }

    @Test
    void whenFindById_returnPurchaseOrder() {

        Mockito.when(purchaseOrderDAO.findById(3L)).thenReturn(Optional.ofNullable(getPurchaseOrder()));

        final PurchaseOrder actual =  purchaseOrderService.findById(3L);
        final PurchaseOrder expected = getPurchaseOrder();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(purchaseOrderDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(purchaseOrderDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  purchaseOrderService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Purchase order with id = " + 100 + " is not found");
        Mockito.verify(purchaseOrderDAO, Mockito.times(1)).findById(100L);
    }

}
