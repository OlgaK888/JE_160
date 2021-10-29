package by.ita.je.service;

import by.ita.je.dao.ReviewDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Review;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceImplTest {

    @Mock
    ReviewDAO reviewDAO;

    @InjectMocks
    ReviewServiceImpl reviewService;

    private Review reviewForTesting() {

        Review review = getReview();
        review.setMessage("Message");
        review.setAccountId(8);
        return review;
    }

    private Review getReview() {
        return new Review();
    }

    @Test
    void whenFindById_returnReview() {

        Mockito.when(reviewDAO.findById(3L)).thenReturn(Optional.ofNullable(getReview()));

        final Review actual =  reviewService.findById(3L);
        final Review expected = getReview();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(reviewDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(reviewDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  reviewService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Review with id = " + 100 + " is not found");
        Mockito.verify(reviewDAO, Mockito.times(1)).findById(100L);
    }

}
