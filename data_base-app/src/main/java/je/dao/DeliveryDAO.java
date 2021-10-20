package je.dao;

import je.model.Delivery;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryDAO extends CrudRepository<Delivery, Long> {
}
