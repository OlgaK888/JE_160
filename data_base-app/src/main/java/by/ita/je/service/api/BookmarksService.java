package by.ita.je.service.api;

import by.ita.je.model.Bookmarks;

public interface BookmarksService {

    Bookmarks create(Bookmarks bookmarks);

    Bookmarks findById(Long id);

    Bookmarks update(Long id, Bookmarks bookmarks);
}
