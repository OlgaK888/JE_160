package by.ita.je.dao;

import by.ita.je.model.Bookmarks;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookmarksDAO extends CrudRepository<Bookmarks, Long> {
}
