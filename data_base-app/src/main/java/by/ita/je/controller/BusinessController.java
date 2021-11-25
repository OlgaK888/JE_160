package by.ita.je.controller;

import by.ita.je.dto.BookmarksDTO;
import by.ita.je.dto.CategoryDTO;
import by.ita.je.dto.ProductDTO;
import by.ita.je.exception.NotCorrectDataException;
import by.ita.je.model.Category;
import by.ita.je.model.Product;
import by.ita.je.model.ShoppingCart;
import by.ita.je.service.api.BusinessService;
import by.ita.je.service.api.ProductService;
import by.ita.je.service.api.ShopService;
import by.ita.je.service.api.ShoppingCartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import by.ita.je.dto.ShoppingCartDTO;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class BusinessController {

    private final ObjectMapper objectMapper;
    private final BusinessService businessService;
    private final ProductService productService;
    private final ShopService shopService;
    private final ShoppingCartService shoppingCartService;

    public BusinessController(ObjectMapper objectMapper, BusinessService businessService, ProductService productService,
                              ShopService shopService, ShoppingCartService shoppingCartService) {
        this.objectMapper = objectMapper;
        this.businessService = businessService;
        this.productService = productService;
        this.shopService = shopService;
        this.shoppingCartService = shoppingCartService;
    }

    @PutMapping("/product/to/shopping/cart/{id_cart}/{id_product}")
    public ShoppingCartDTO addProductToShoppingCart(@PathVariable ("id_cart") Long idCart,
                                                    @PathVariable ("id_product") Long idProduct) {

        return objectMapper.convertValue(businessService.addProductToShoppingCart(idCart, idProduct),
                ShoppingCartDTO.class);

    }

    @PutMapping("/product/to/shopping/cart/by/account/{id_account}/{id_product}")
    public ShoppingCartDTO addProductToShoppingCartByCurrentAccount(@PathVariable ("id_account") Long idAccount,
                                                                    @PathVariable ("id_product") Long idProduct) {

        return objectMapper.convertValue(businessService.addProductToShoppingCartByCurrentAccount(idAccount, idProduct),
                ShoppingCartDTO.class);
    }

    @PutMapping("/product/to/bookmarks/by/account/{id_account}/{id_product}")
    public BookmarksDTO addProductToBookmarksByCurrentAccount(@PathVariable ("id_account") Long idAccount,
                                                              @PathVariable ("id_product") Long idProduct) {

        return objectMapper.convertValue(businessService.addProductToBookmarksByCurrentAccount(idAccount, idProduct),
                BookmarksDTO.class);
    }

    @GetMapping("/all/product/in/cart/{id_cart}")
    public Collection<ProductDTO> getAllProductsInShoppingCart(@PathVariable ("id_cart") Long idCart) {

        Collection<Product> productCollection = businessService.findAllProductsInShoppingCart(idCart);
        Collection<ProductDTO> productDTOCollection = productCollection.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOCollection;

    }

    @GetMapping("/all/product/in/cart/by/account/{id_account}")
    public Collection<ProductDTO> findAllProductsInShoppingCartByCurrentAccount(@PathVariable ("id_account") Long idAccount) {

        Collection<Product> productCollection = businessService.findAllProductsInShoppingCartByCurrentAccount(idAccount);
        Collection<ProductDTO> productDTOCollection = productCollection.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOCollection;
    }

    @GetMapping("/products/by/category")
    public Collection<ProductDTO> getProductsByCategory(@RequestParam(value = "id", required = true) Long id) {

        Collection<Product> productCollection = businessService.getProductsByCategory(id);
        Collection<ProductDTO> productDTOCollection = productCollection.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOCollection;

    }

    @GetMapping("/category/by/product")
    public CategoryDTO getCategoryByProduct(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(businessService.getCategoryByProduct(id), CategoryDTO.class);
    }

}
