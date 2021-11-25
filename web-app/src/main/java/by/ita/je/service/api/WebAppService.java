package by.ita.je.service.api;

import by.ita.je.dto.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface WebAppService {

    AccountDTO getAccount(Long id);

    ShoppingCartDTO getShoppingCartByCurrentUser();

    Collection<ProductDTO> getProductCatalog();

    Collection<ShopDTO> getAllShops();

    Collection<CategoryDTO> getAllCategories();

    Collection<ProductDTO> findAllProductsByCategory(Long id);

    ProductDTO getProduct(Long id);

    ShoppingCartDTO putProductToShoppingCart(Long idCart, Long idProduct);

    List<ProductDTO> findByFilter(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                  double ratingFrom, double ratingTo);

    List<ProductDTO> findByPartOfName(String partOfName);
}
