package by.ita.je.service;

import by.ita.je.dao.BookmarksDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Bookmarks;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class BookmarksServiceImplTest {

    @Mock
    BookmarksDAO bookmarksDAO;

    @InjectMocks
    BookmarksServiceImpl bookmarksService;

    private Bookmarks bookmarksForTesting() {

        Bookmarks bookmarks = getBookmarks();
        return bookmarks;
    }

    private Bookmarks getBookmarks() {
        return new Bookmarks();
    }

    @Test
    void whenCreate_returnBookmarks() {

        Bookmarks bookmarksForTesting = bookmarksForTesting();
        Mockito.when(bookmarksDAO.save(bookmarksForTesting)).thenReturn(getBookmarks());

        final Bookmarks actual = bookmarksService.create(bookmarksForTesting);
        final Bookmarks expected = getBookmarks();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(bookmarksDAO,Mockito.times(1)).save(bookmarksForTesting);
    }

    @Test
    void whenFindById_returnBookmarks() {

        Mockito.when(bookmarksDAO.findById(3L)).thenReturn(Optional.ofNullable(getBookmarks()));

        final Bookmarks actual =  bookmarksService.findById(3L);
        final Bookmarks expected = getBookmarks();

        Assertions.assertEquals(expected, actual);
        Mockito.verify(bookmarksDAO, Mockito.times(1)).findById(3L);
    }

    @Test
    void whenFindById_throwNotFoundDataException() {

        Mockito.when(bookmarksDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  bookmarksService.findById(100L));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Bookmarks with id = " + 100 + " are not found");
        Mockito.verify(bookmarksDAO, Mockito.times(1)).findById(100L);
    }

    @Test
    void whenUpdate_returnBookmarks() {

        Bookmarks bookmarksForTesting = bookmarksForTesting();
        Mockito.when(bookmarksDAO.findById(1L)).thenReturn(Optional.ofNullable(bookmarksForTesting));
        Mockito.when(bookmarksDAO.save(bookmarksForTesting)).thenReturn(getBookmarks());

        final Bookmarks actual =  bookmarksService.update(1L, bookmarksForTesting);
        final Bookmarks expected = getBookmarks();
        Assertions.assertEquals(expected, actual);

        Mockito.verify(bookmarksDAO, Mockito.times(1)).findById(1L);
        Mockito.verify(bookmarksDAO, Mockito.times(1)).save(bookmarksForTesting);
    }

    @Test
    void whenUpdate_throwNotFoundDataException() {

        Bookmarks bookmarksForTesting = bookmarksForTesting();
        Mockito.when(bookmarksDAO.findById(100L)).thenReturn(Optional.empty());

        NotFoundDataException notFoundDataException = Assertions.assertThrows(NotFoundDataException.class,
                () ->  bookmarksService.update(100L, bookmarksForTesting()));
        Assertions.assertEquals(notFoundDataException.getMessage(), "Bookmarks with id = " + 100 + " are not found");
        Mockito.verify(bookmarksDAO, Mockito.times(1)).findById(100L);
    }
}
