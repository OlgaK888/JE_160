package by.ita.je.service.api;

import by.ita.je.model.Account;
import by.ita.je.model.Product;
import by.ita.je.model.ShoppingCart;

import java.util.Collection;

public interface BusinessService {

    ShoppingCart addProductToShoppingCart(Long idCart, Long idProduct);

    Account findById(Long idAccount);

    Product getProductToShop(Long idProduct);

    Collection<Product> findAllProductsInShoppingCart(Long idCart);

    //Collection<Product> findProductsByCategory(String categoryName);

    Collection<Product> getProductsByCategory(String id);

}


