package by.ita.je.service;

import by.ita.je.dao.StatusDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Status;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StatusServiceImplTest {

    @Mock
    StatusDAO statusDAO;

    @InjectMocks
    StatusServiceImpl statusService;

    private Status statusForTesting() {

        Status status = getStatus();
        status.setName("ok");
        return status;
    }

    private Status getStatus() {
        return new Status();
    }

    @Test
    void whenFindById_returnStatus() {

        Mockito.when(statusDAO.findById(3L)).thenReturn(Optional.ofNullable(getStatus()));

        final Status actual =  statusService.findById(3L);
        final Status expected = getStatus();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(statusDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(statusDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  statusService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Status with id = " + 100 + " is not found");
        Mockito.verify(statusDAO, Mockito.times(1)).findById(100L);
    }

}
