package by.ita.je.dao;

import by.ita.je.model.PurchaseOrder;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderDAO extends CrudRepository<PurchaseOrder, Long> {
}
