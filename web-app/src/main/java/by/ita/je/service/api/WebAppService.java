package by.ita.je.service.api;

import by.ita.je.dto.CategoryDTO;
import by.ita.je.dto.ProductDTO;
import by.ita.je.dto.ShopDTO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

public interface WebAppService {

    Collection<ProductDTO> getProductCatalog();

    Collection<ShopDTO> getAllShops();

    Collection<CategoryDTO> getAllCategories();

    //Collection<ProductDTO> getAllProductsByCategory(String categoryName);

    Collection<ProductDTO> findAllProductsByCategory(String id);

    ProductDTO getProduct(Long id);

    List<ProductDTO> findByPartOfName(String partOfName);
}
