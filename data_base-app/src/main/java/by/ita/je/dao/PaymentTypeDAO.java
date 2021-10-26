package by.ita.je.dao;

import by.ita.je.model.PaymentType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentTypeDAO extends CrudRepository<PaymentType, Long> {
}
