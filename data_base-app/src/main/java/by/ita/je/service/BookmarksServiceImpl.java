package by.ita.je.service;

import by.ita.je.dao.BookmarksDAO;
import by.ita.je.exception.NotCorrectDataException;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Bookmarks;
import by.ita.je.service.api.BookmarksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class BookmarksServiceImpl implements BookmarksService {

    private final BookmarksDAO bookmarksDAO;

    @Transactional
    @Override
    public Bookmarks create(Bookmarks bookmarks) throws NotCorrectDataException{

        try {

            final Bookmarks bookmarks1 = bookmarksDAO.save(bookmarks);
            return bookmarks1;

        } catch (Exception e) {

            throw new NotCorrectDataException("Not correct data");
        }
    }

    @Override
    public Bookmarks findById(Long id) {

        final Bookmarks bookmarks = bookmarksDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Bookmarks with id = " + id + " are not found"));
        return bookmarks;
    }

    @Transactional
    @Override
    public Bookmarks update(Long id, Bookmarks bookmarks) {

        final Bookmarks updatedBookmarks = bookmarksDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Bookmarks with id = " + id +
                        " are not found"));

        return bookmarksDAO.save(updatedBookmarks);
    }

}