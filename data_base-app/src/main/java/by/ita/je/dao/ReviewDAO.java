package by.ita.je.dao;

import by.ita.je.model.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewDAO extends CrudRepository<Review, Long> {
}
