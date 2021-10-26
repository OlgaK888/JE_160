package by.ita.je.dao;

import by.ita.je.model.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDAO extends CrudRepository<Delivery, Long> {
}
