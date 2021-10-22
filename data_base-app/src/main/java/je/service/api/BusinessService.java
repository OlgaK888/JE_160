package je.service.api;

import je.model.Account;
import je.model.Product;
import je.model.ShoppingCart;

public interface BusinessService {

    ShoppingCart addProductToShoppingCart(Long idProduct, Long idCart);

    Account findById(Long idAccount);

    Product getProductToShop(Long idProduct);

}


