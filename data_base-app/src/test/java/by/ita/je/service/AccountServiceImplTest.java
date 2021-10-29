package by.ita.je.service;

import by.ita.je.dao.AccountDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Account;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class AccountServiceImplTest {

    @Mock
    AccountDAO accountDAO;

    @InjectMocks
    AccountServiceImpl accountService;

    private Account accountForTesting() {

        Account account = getAccount();
        account.setName("Michail");
        account.setPhoneNumber(375291234567L);
        account.setCountry("Belarus");
        account.setEmail("michail@mail.ru");
        return account;
    }

    private Account getAccount() {
        return new Account();
    }

    @Test
    void whenFindById_returnAccount() {

        Mockito.when(accountDAO.findById(3L)).thenReturn(Optional.ofNullable(getAccount()));

        final Account actual =  accountService.findById(3L);
        final Account expected = getAccount();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(accountDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(accountDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  accountService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Account with id = " + 100 + " is not found");
        Mockito.verify(accountDAO, Mockito.times(1)).findById(100L);
    }

}
