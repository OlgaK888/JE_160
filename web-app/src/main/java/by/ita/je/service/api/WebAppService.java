package by.ita.je.service.api;

import by.ita.je.dto.CategoryDTO;
import by.ita.je.dto.ProductDTO;
import by.ita.je.dto.ShopDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface WebAppService {

    Collection<ProductDTO> getProductCatalog();

    Collection<ShopDTO> getAllShops();

    Collection<CategoryDTO> getAllCategories();

    Collection<ProductDTO> findAllProductsByCategory(Long id);

    ProductDTO getProduct(Long id);

    List<ProductDTO> findByFilter(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                  double ratingFrom, double ratingTo);

    List<ProductDTO> findByPartOfName(String partOfName);
}
