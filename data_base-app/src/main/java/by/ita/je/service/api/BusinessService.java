package by.ita.je.service.api;

import by.ita.je.model.*;

import java.util.Collection;
import java.util.List;

public interface BusinessService {

    ShoppingCart addProductToShoppingCart(Long idCart, Long idProduct);

    ShoppingCart addProductToShoppingCartByCurrentAccount(Long idAccount, Long idProduct);

    Bookmarks addProductToBookmarksByCurrentAccount(Long idAccount, Long idProduct);

    Collection<Product> findAllProductsInShoppingCart(Long idCart);

    List<Product> findAllProductsInShoppingCartByCurrentAccount(Long idAccount);

    Collection<Product> getProductsByCategory(Long id);

    Category getCategoryByProduct(Long id);

}


