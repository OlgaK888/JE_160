package je.dao;

import je.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDAO extends CrudRepository<Account, Long> {
}
