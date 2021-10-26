package by.ita.je.dao;

import by.ita.je.model.Status;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusDAO extends CrudRepository<Status, Long> {
}
