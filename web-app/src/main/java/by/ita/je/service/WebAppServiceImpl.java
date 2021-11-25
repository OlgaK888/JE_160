package by.ita.je.service;

import by.ita.je.dto.*;
import by.ita.je.models.User;
import by.ita.je.service.api.WebAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class WebAppServiceImpl implements WebAppService {

    private final RestTemplate restTemplate;
    private final UserDetailsServiceImpl userDetailsService;
    private final String url="http://localhost:8003/data_base-app/";

    @Override
    public AccountDTO getAccount(Long id) {
        AccountDTO accountDTO = restTemplate.getForObject(url +"account?id=" + id,
                AccountDTO.class);
        return accountDTO;
    }

    @Override
    public ShoppingCartDTO getShoppingCartByCurrentUser(){
        User user = userDetailsService.getCurrentUser();
        long accountId = user.getAccountId();
        ShoppingCartDTO shoppingCartDTO = restTemplate.getForObject(url +"/all/product/in/cart/by/account/" + accountId,
                ShoppingCartDTO.class);
        return shoppingCartDTO;
    }

    @Override
    public Collection<ProductDTO> getProductCatalog(){
        ResponseEntity<ProductDTO[]> responseEntity = restTemplate.getForEntity(url + "products",
                ProductDTO[].class);
        List<ProductDTO> list = List.of(responseEntity.getBody());
        return list;
    }

    @Override
    public Collection<ShopDTO> getAllShops() {
        ResponseEntity<ShopDTO[]> responseEntity = restTemplate.getForEntity(url + "shops",
                ShopDTO[].class);
        List<ShopDTO> list = List.of(responseEntity.getBody());
        return list;
    }

    @Override
    public Collection<CategoryDTO> getAllCategories() {
        ResponseEntity<CategoryDTO[]> responseEntity = restTemplate.getForEntity(url + "categories",
                CategoryDTO[].class);
        List<CategoryDTO> list = List.of(responseEntity.getBody());
        return list;
    }

    @Override
    public Collection<ProductDTO> findAllProductsByCategory(Long id) {
        ResponseEntity<ProductDTO[]> responseEntity = restTemplate.getForEntity(url
                + "products/by/category?id=" + id, ProductDTO[].class);
        List<ProductDTO> list = List.of(responseEntity.getBody());
        return list;
    }

    @Override
    public ProductDTO getProduct(Long id) {
        ProductDTO productDTO = restTemplate.getForObject(url +"product?id=" + id,
                ProductDTO.class);
        return productDTO;
    }

    @Override
    public ShoppingCartDTO putProductToShoppingCart(Long idCart, Long idProduct) {
        ProductDTO productDTO = restTemplate.getForObject(url +"product?id=" + idProduct,
                ProductDTO.class);
        ShoppingCartDTO cartDTO = new ShoppingCartDTO();
        cartDTO.setProducts(List.of(productDTO));
        return cartDTO;
        //ShoppingCartDTO cartDTOWithProduct = restTemplate.put(url + "/product/to/shopping/cart/1/" + idProduct, cartDTO,   );
        //ShoppingCartDTO cartDTO = restTemplate.getForObject(url + "/product/to/shopping/cart/1/" + idProduct,
          //      ShoppingCartDTO.class);
    }

    @Override
    public List<ProductDTO> findByFilter(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                         double ratingFrom, double ratingTo) {
        FilterByCategoryPriceRatingDTO filteredDto = new FilterByCategoryPriceRatingDTO(nameCategory, priceFrom, priceTo,
                ratingFrom, ratingTo);
        ResponseEntity<ProductDTO[]> responseEntity = restTemplate.postForEntity(url + "/search/category/price/rating",
                filteredDto, ProductDTO[].class);
        List<ProductDTO> list = Arrays.asList(Objects.requireNonNull(responseEntity.getBody()));
        return list;
    }

    @Override
    public List<ProductDTO> findByPartOfName(String partOfName) {
        FilterByPartOfNameDTO filteredDto = new FilterByPartOfNameDTO(partOfName);
            ResponseEntity<ProductDTO[]> responseEntity = restTemplate.postForEntity(url + "search/partial",
                    filteredDto, ProductDTO[].class);
        List<ProductDTO> list = List.of(responseEntity.getBody());
        return list;
    }

}
