package je.dao;

import je.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDAO extends CrudRepository<Review, Long> {
}
