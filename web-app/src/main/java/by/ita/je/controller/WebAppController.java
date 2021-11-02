package by.ita.je.controller;

import by.ita.je.dto.CategoryDTO;
import by.ita.je.dto.FilterByCategoryPriceRatingDTO;
import by.ita.je.dto.ProductDTO;
import by.ita.je.dto.ShopDTO;
import by.ita.je.service.api.WebAppService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

@AllArgsConstructor
@Controller
public class WebAppController {

    private final WebAppService service;

    @GetMapping(value = "/")
    public String home() {
        return "home_page";
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

    @GetMapping("/403")
    public String error403() {
        return "403";
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

}


