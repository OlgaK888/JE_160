package by.ita.je.service;

import by.ita.je.dao.CommentDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Comment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CommentServiceImplTest {

    @Mock
    CommentDAO commentDAO;

    @InjectMocks
    CommentServiceImpl commentService;

    private Comment commentForTesting() {

        Comment comment = getComment();
        comment.setMessage("Message");
        comment.setAccountId(8);
        return comment;
    }

    private Comment getComment() {
        return new Comment();
    }

    @Test
    void whenFindById_returnComment() {

        Mockito.when(commentDAO.findById(3L)).thenReturn(Optional.ofNullable(getComment()));

        final Comment actual =  commentService.findById(3L);
        final Comment expected = getComment();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(commentDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(commentDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  commentService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Comment with id = " + 100 + " is not found");
        Mockito.verify(commentDAO, Mockito.times(1)).findById(100L);
    }

}
