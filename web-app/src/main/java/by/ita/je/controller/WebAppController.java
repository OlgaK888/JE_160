package by.ita.je.controller;

import by.ita.je.dto.*;
import by.ita.je.models.User;
import by.ita.je.service.UserDetailsServiceImpl;
import by.ita.je.service.api.WebAppService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class WebAppController {

    private final UserDetailsServiceImpl userDetailsService;
    private final WebAppService service;

    @GetMapping(value = "/")
    public String home() {
        return "home_page";
    }

    @GetMapping(value = "/profile")
    public String getProfile(Model model) {
        User user = userDetailsService.getCurrentUser();
        AccountDTO accountDTO = service.getAccount(user.getId());
        model.addAttribute("user", user);
        return "profile";
    }

    @GetMapping(value = "/products/catalog")
    public String getProductCatalog(Model model){
        model.addAttribute("productDTOs", service.getProductCatalog());
        return "product_catalog";
    }

    @GetMapping(value = "/category/list")
    public String getAllCategories(Model model){
        model.addAttribute("categoryDTOs", service.getAllCategories());
        return "catalog";
    }

    @GetMapping(value = "/category/products")
    public String findAllProductsByCategory(@RequestParam(value = "id", required = true) Long id, Model model){
        model.addAttribute("productDTOs", service.findAllProductsByCategory(id));
        return "product_catalog";
    }


    @GetMapping(value = "/shops/list")
    public String getAllShops(Model model){
        model.addAttribute("shopDTOs", service.getAllShops());
        return "shop_list";
    }

    @GetMapping(value = "/every/product")
    public String getProduct(@RequestParam(value = "id", required = true) Long id, Model model){
        model.addAttribute("productDTO", service.getProduct(id));
        return "product";
    }

    @PutMapping(value = "/product/to/cart")
    public String putProductToShoppingCart(@RequestParam(value = "id_product", required = true) Long idProduct,
                                           Model model){
        model.addAttribute("cartDTO", service.putProductToShoppingCart(1L, idProduct));
        return "shopping_cart";
    }

    /*@GetMapping(value = "/product/to/cart")
    public String cart() {
        return "shopping_cart";
    }*/

    @GetMapping(value = "/all/products/to/cart")
    public String getProductsToShoppingCartByAccount(Model model){
        model.addAttribute("cartDTO", service.getShoppingCartByCurrentUser());
        return "products_in_shopping_cart";
    }

    @GetMapping("/search/filtered")
    public String findByFilter(@ModelAttribute FilterByCategoryPriceRatingDTO filteredDto, Model model) {
        model.addAttribute("productDTOs", service.findByFilter(filteredDto.getNameCategory(),
                filteredDto.getPriceFrom(), filteredDto.getPriceTo(),
                filteredDto.getRatingFrom(), filteredDto.getRatingTo()));
        return "product_catalog";
    }

    @GetMapping(value = "/search/partial")
    public String findByPartOfName(@RequestParam(value = "part_of_name", required = true) String partOfName,
            Model model) {
            model.addAttribute("productDTOs", service.findByPartOfName(partOfName));
        return "product_catalog";
    }

    @ModelAttribute("productDTO")
    private ProductDTO createProductDTOModel() {
        return new ProductDTO();
    }

    @ModelAttribute("shopDTO")
    private ShopDTO createShopDTOModel() {
        return new ShopDTO();
    }

    @ModelAttribute("categoryDTO")
    private CategoryDTO createCategoryDTOModel() {
        return new CategoryDTO();
    }

    @ModelAttribute("cartDTO")
    private ShoppingCartDTO createShoppingCartDTOModel() {
        return new ShoppingCartDTO();
    }

}


