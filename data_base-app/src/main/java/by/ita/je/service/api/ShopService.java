package by.ita.je.service.api;

import by.ita.je.model.Shop;

import java.util.Collection;

public interface ShopService {

    Shop findById(Long id);

    Collection<Shop> findAllShops();
}
