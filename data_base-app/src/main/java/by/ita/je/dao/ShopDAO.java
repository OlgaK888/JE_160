package by.ita.je.dao;

import by.ita.je.model.Shop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopDAO extends CrudRepository<Shop, Long> {
}
