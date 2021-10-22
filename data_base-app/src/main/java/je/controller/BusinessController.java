package je.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import je.dto.AccountDTO;
import je.dto.ProductDTO;
import je.dto.ShopDTO;
import je.dto.ShoppingCartDTO;
import je.model.Product;
import je.model.Shop;
import je.service.api.BusinessService;
import je.service.api.ProductService;
import je.service.api.ShopService;
import je.service.api.ShoppingCartService;
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


    @PutMapping("/product/to/shopping/cart/{id_product}")
    public ShoppingCartDTO addProductToShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO,
                                                    @PathVariable ("id_product") Long idProduct) {

        return objectMapper.convertValue(businessService.addProductToShoppingCart(idProduct, shoppingCartDTO.getId()),
                ShoppingCartDTO.class);

    }

    @GetMapping("/product/to/shopping/{id_product}")
    public ProductDTO getProductToShop(@PathVariable ("id_product") Long idProduct) {

        return objectMapper.convertValue(businessService.getProductToShop(idProduct), ProductDTO.class);

    }

    @GetMapping("/product/to/shopping/cart/{id_account}/{id_product}")
    public AccountDTO addProductsToShoppingCart(@PathVariable ("id_account") Long idAccount,
                                                @PathVariable ("id_product") Long idProduct) {

        //final Account account = objectMapper
          //      .convertValue(businessService.addProductToShoppingCart(idAccount, idProduct), Account.class);

        return objectMapper.convertValue(businessService
                .addProductToShoppingCart(idAccount, idProduct), AccountDTO.class);

    }

    @GetMapping("/products")
    public Collection<ProductDTO> findAllProducts(){

        Collection<Product> productCollection = productService.findAllProducts();
        Collection<ProductDTO> productDTOCollection = productCollection.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOCollection;

    }

    @GetMapping("/shops")
    public Collection<ShopDTO> findAllShops(){

        Collection<Shop> shopCollection = shopService.findAllShops();
        Collection<ShopDTO> shopDTOCollection = shopCollection.stream()
                .map(shop -> objectMapper.convertValue(shop, ShopDTO.class))
                .collect(Collectors.toList());

        return shopDTOCollection;

    }

}
