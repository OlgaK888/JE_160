package je.dao;

import je.model.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopDAO extends CrudRepository<Shop, Long> {
}
