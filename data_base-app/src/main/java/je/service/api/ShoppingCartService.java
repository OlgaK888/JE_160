package je.service.api;

import je.model.ShoppingCart;

public interface ShoppingCartService {

    ShoppingCart create(ShoppingCart shoppingCart);

    ShoppingCart findById(Long id);

    ShoppingCart update(Long id, ShoppingCart shoppingCart);
}
