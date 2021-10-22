package je.service.api;

import je.model.Shop;

import java.util.Collection;

public interface ShopService {

    Shop findById(Long id);

    Collection<Shop> findAllShops();
}
