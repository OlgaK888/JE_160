package by.ita.je.service;

import by.ita.je.dao.PaymentTypeDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.PaymentType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class PaymentTypeServiceImplTest {

    @Mock
    PaymentTypeDAO paymentTypeDAO;

    @InjectMocks
    PaymentTypeServiceImpl paymentTypeService;

    private PaymentType paymentTypeForTesting() {

        PaymentType paymentType = getPaymentType();
        paymentType.setTypeName("abcd");
        return paymentType;
    }

    private PaymentType getPaymentType() {
        return new PaymentType();
    }

    @Test
    void whenFindById_returnPaymentType() {

        Mockito.when(paymentTypeDAO.findById(3L)).thenReturn(Optional.ofNullable(getPaymentType()));

        final PaymentType actual =  paymentTypeService.findById(3L);
        final PaymentType expected = getPaymentType();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(paymentTypeDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(paymentTypeDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  paymentTypeService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Payment type with id = " + 100 + " is not found");
        Mockito.verify(paymentTypeDAO, Mockito.times(1)).findById(100L);
    }

}
