package by.ita.je.controller;

import by.ita.je.dto.ProductDTO;
import by.ita.je.model.Product;
import by.ita.je.service.api.BusinessService;
import by.ita.je.service.api.ProductService;
import by.ita.je.service.api.ShopService;
import by.ita.je.service.api.ShoppingCartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import by.ita.je.dto.ShoppingCartDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
public class BusinessController {

    private final ObjectMapper objectMapper;
    private final BusinessService businessService;
    private final ProductService productService;
    private final ShopService shopService;
    private final ShoppingCartService shoppingCartService;

    public BusinessController(ObjectMapper objectMapper, BusinessService businessService, ProductService productService, ShopService shopService, ShoppingCartService shoppingCartService) {
        this.objectMapper = objectMapper;
        this.businessService = businessService;
        this.productService = productService;
        this.shopService = shopService;
        this.shoppingCartService = shoppingCartService;
    }


    /*@GetMapping("/product/to/shopping/cart/{id}")
    public AccountDTO getAccount(@PathVariable ("id") Long id) {

        return objectMapper.convertValue(businessService.findById(id), AccountDTO.class);

    }*/


    @PutMapping("/product/to/shopping/cart/{id_cart}/{id_product}")
    public ShoppingCartDTO addProductToShoppingCart(@PathVariable ("id_cart") Long idCart,
                                                    @PathVariable ("id_product") Long idProduct) {

        return objectMapper.convertValue(businessService.addProductToShoppingCart(idCart, idProduct),
                ShoppingCartDTO.class);

    }

    @GetMapping("/all/product/in/cart/{id_cart}")
    public Collection<ProductDTO> getAllProductsInShoppingCart(@PathVariable ("id_cart") Long idCart) {

        Collection<Product> productCollection = businessService.findAllProductsInShoppingCart(idCart);
        Collection<ProductDTO> productDTOCollection = productCollection.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOCollection;

    }

    /*@GetMapping("/products/by/category/{name}")
    public Collection<ProductDTO> findProductsByCategory(@PathVariable ("name") String categoryName) {

        Collection<Product> productCollection = businessService.findProductsByCategory(categoryName);
        Collection<ProductDTO> productDTOCollection = productCollection.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOCollection;

    }*/

    @GetMapping("/products/by/category/{id}")
    public Collection<ProductDTO> getProductsByCategory(@PathVariable ("id") String id) {

        Collection<Product> productCollection = businessService.getProductsByCategory(id);
        Collection<ProductDTO> productDTOCollection = productCollection.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOCollection;

    }





}
