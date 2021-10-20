package je.dao;

import je.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDAO extends CrudRepository<Status, Long> {
}
