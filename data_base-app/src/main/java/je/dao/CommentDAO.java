package je.dao;

import je.model.Comment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDAO extends CrudRepository<Comment, Long> {
}
