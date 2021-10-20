package je.dao;

import je.model.PaymentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeDAO extends CrudRepository<PaymentType, Long> {
}
