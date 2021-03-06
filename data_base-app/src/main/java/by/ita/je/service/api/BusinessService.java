package by.ita.je.service.api;

import by.ita.je.model.Account;
import by.ita.je.model.Category;
import by.ita.je.model.Product;
import by.ita.je.model.ShoppingCart;

import java.util.Collection;
import java.util.List;

public interface BusinessService {

    ShoppingCart addProductToShoppingCart(Long idCart, Long idProduct);

    Collection<Product> findAllProductsInShoppingCart(Long idCart);

    //Collection<Product> findProductsByCategory(String categoryName);

    Collection<Product> getProductsByCategory(Long id);

    Category getCategoryByProduct(Long id);

}


