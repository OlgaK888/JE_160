package by.ita.je.controller;

import by.ita.je.dto.*;
import by.ita.je.model.Product;
import by.ita.je.service.api.WebAppService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Controller
public class WebAppController {

    //private final String url="http://localhost:8003/data_base-app/";
    private final WebAppService service;

    @GetMapping(value = "/")
    public String home() {
        return "home_page";
    }

    /*@GetMapping(value = "/products/catalog")
    public String getProductCatalog(Model model){
        ResponseEntity<ProductDTO[]> responseEntity = restTemplate.getForEntity(url + "products",
                ProductDTO[].class);
        List<ProductDTO> list = List.of(responseEntity.getBody());
        model.addAttribute("productdtos", list);
        return "product_catalog";
    }*/

    @GetMapping(value = "/products/catalog")
    public String getProductCatalog(Model model){
        model.addAttribute("productdtos", service.getProductCatalog());
        return "product_catalog";
    }

    @GetMapping(value = "/category/list")
    public String getAllCategories(Model model){
        model.addAttribute("categorydtos", service.getAllCategories());
        return "catalog";
    }

    /*@GetMapping(value = "/category/products/{name}")
    //public String getProductsByCategory(@RequestParam(value = "name", required = true)String name, Model model){
    public String getAllProductsByCategory(@PathVariable ("name") String categoryName, Model model){
        model.addAttribute("productdtos", service.getAllProductsByCategory(categoryName));
        return "product_catalog";
    }*/

    @GetMapping(value = "/category/products/{id}")
    public String findAllProductsByCategory(@PathVariable ("id") String id, Model model){
        model.addAttribute("productdtos", service.findAllProductsByCategory(id));
        return "product_catalog";
    }

    /*@GetMapping(value = "/shops/list")
    public String getAllShop(Model model){
        ResponseEntity<ShopDTO[]> responseEntity = restTemplate.getForEntity(url + "shops",
                ShopDTO[].class);
        List<ShopDTO> list = List.of(responseEntity.getBody());
        model.addAttribute("shopdtos", list);
        return "shop_list";
    }*/

    @GetMapping(value = "/shops/list")
    public String getAllShops(Model model){
        model.addAttribute("shopdtos", service.getAllShops());
        return "shop_list";
    }

    @GetMapping(value = "/every/product")
    public String getProduct(@RequestParam(value = "id", required = true) Long id, Model model){
        model.addAttribute("productdto", service.getProduct(id));
        return "product";
    }

    @GetMapping(value = "/search/partial")
    public String findByPartOfName(@RequestParam(value = "part_of_name", required = true) String partOfName,
            Model model) {
            model.addAttribute("productdtos", service.findByPartOfName(partOfName));
        return "product_catalog";
    }

    /*@GetMapping(value = "/search/filtration")
    public String findByCategoryPriceRating(@RequestParam(value = "name_category", required = true) String nameCategory,
                                            @RequestParam(value = "price_from", required = true) BigDecimal priceFrom,
                                            @RequestParam(value = "price_to", required = true) BigDecimal priceTo,
                                            @RequestParam(value = "rating_from", required = true) double ratingFrom,
                                            @RequestParam(value = "rating_to", required = true) double ratingTo,
                                   Model model) {
        FilterByCategoryPriceRatingDTO filteredDto = new FilterByCategoryPriceRatingDTO(nameCategory,
                priceFrom, priceTo, ratingFrom, ratingTo);
        if (filteredDto.getNameCategory() != "" || filteredDto.getIndexOfCourseFirst() > 0 ||
                filteredDto.getIndexOfCourseLast() > 0) {
            ResponseEntity<ResearchProjectDto[]> responseEntity = restTemplate.postForEntity(url + "search/JPQL",
                    filteredDto, ResearchProjectDto[].class);
            List<ResearchProjectDto> list = Arrays.asList(responseEntity.getBody());
        model.addAttribute("productdtos", service.findByPartOfName(partOfName));
        return "product_catalog";
    }*/

    /*public Collection<Product> findByCategoryPriceRating(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                                         double ratingFrom, double ratingTo)*/

    /*@GetMapping(value = "/search/jpql")
    public String findJPQL(
            @RequestParam(value = "name_research_project", required = false) String nameResearchProjectFromStudent,
            @RequestParam(value = "index_of_course_first", required = false) Byte indexOfCourseFirst,
            @RequestParam(value = "index_of_course_last", required = false) Byte indexOfCourseLast,
            Model model) {
        FilteredDto filteredDto = new FilteredDto(nameResearchProjectFromStudent, indexOfCourseFirst, indexOfCourseLast);
        if (filteredDto.getNameResearchProjectFromStudent() != "" || filteredDto.getIndexOfCourseFirst() > 0 ||
                filteredDto.getIndexOfCourseLast() > 0) {
            ResponseEntity<ResearchProjectDto[]> responseEntity = restTemplate.postForEntity(url + "search/JPQL",
                    filteredDto, ResearchProjectDto[].class);
            List<ResearchProjectDto> list = Arrays.asList(responseEntity.getBody());
            model.addAttribute("projectdtos", list);
        }
        return "project_list";
    }*/

    @ModelAttribute("productdto")
    private ProductDTO createProductDTOModel() {
        return new ProductDTO();
    }

    @ModelAttribute("shopdto")
    private ShopDTO createShopDTOModel() {
        return new ShopDTO();
    }

    @ModelAttribute("categorydto")
    private CategoryDTO createCategoryDTOModel() {
        return new CategoryDTO();
    }

    //<p th:text="'Category: ' + ${productdto.category.name}" /> from product.html line 19

    //<form action="/web-app/category/products/${categorydto.name}" method="get" >  from catalog.html line 21
    //<a th:href="@{/web-app/category/products/{name}(name=${categorydto.name})}"></a>
            //<button type="submit" >Products</button>

    /*<!DOCTYPE html>   from catalog.html
<html xmlns:th="http://www.thymeleaf.org">
<html lang="en">
            <<head>
    <meta charset="utf-8">
    <title>Shop name</title>
    <link rel="stylesheet" href="catalog.css">
</head>
<body>
<div class="catalog">
    <div class="text">Catalog</div>
</div>
</div>
<form action="/web-app/" method="get">
    <button type="submit">To home page</button>
</form>
<div class="productContainer">
    <div class="productCard">
        <form id="category" method="get">
            <input name="name" type="text" th:value= "${categorydto.name}" id="id" hidden="true"/>
        </form>
        <img class="productImg" src="https://images.by.prom.st/177392359_w640_h640_177392359.jpg">
        <p th:text="'${categorydto.name}" />
        <span class="productText">Sofas</span>
        <button type="submit">Sofas</button>
    </div>
    <div class="productCard">
        <img class="productImg" src="https://smolensk.pinskdrev.ru/web/files/imagick_cache/w580h580t3/web/catalogfiles/catalog/offers/alt_venge.jpg">
        <span class="productText">Tables</span>
        <button type="submit">Tables</button>
    </div>
    <div class="productCard">
        <img class="productImg" src="https://assets.thefurnish.ru/system/uploads/product_image/image/214543/h-10928.jpg">
        <span class="productText">Armchairs</span>
        <button type="submit">Armchairs</button>
    </div>
    <div class="productCard">
        <img class="productImg" src="https://kingstyle.by/upload/iblock/3cb/3cbbaeba9e9d1346f3e7299cc2942b6b.jpg">
        <span class="productText">Chairs</span>
        <button type="submit">Chairs</button>
    </div>
    <div class="productCard">

        <span class="productText">Beds</span>
        <button type="submit">Beds</button>
    </div>
</div>
</body>
</html>*/

    /*<!DOCTYPE html>                              from catalog.html 2
<html xmlns:th="http://www.thymeleaf.org">
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Shop name</title>
    <link rel="stylesheet" href="catalog.css">
</head>
<body>
<div class="catalog">
    <div class="text">Catalog</div>
</div>
<br>
<br>
<form action="/web-app/" method="get">
    <button type="submit">To home page</button>
</form>
<br>
<br>
<form id="category" method="get">
    <input name="name" type="text" th:value= "${categorydto.name}" id="id" hidden="true"/>
</form>
<form id="sofa" method="get">
    <img class="productImg" src="https://images.by.prom.st/177392359_w640_h640_177392359.jpg">
    <button type="submit">Sofas</button>
</form>
<form id="table" method="get">
    <img class="productImg" src="https://smolensk.pinskdrev.ru/web/files/imagick_cache/w580h580t3/web/catalogfiles/catalog/offers/alt_venge.jpg">
    <button type="submit">Tables</button>
</form>
<form id="armchair" method="get">
    <img class="productImg" src="https://assets.thefurnish.ru/system/uploads/product_image/image/214543/h-10928.jpg">
    <button type="submit">Armchairs</button>
</form>
<form id="chair" method="get">
    <img class="productImg" src="https://kingstyle.by/upload/iblock/3cb/3cbbaeba9e9d1346f3e7299cc2942b6b.jpg">
    <button type="submit">Chairs</button>
</form>
<form id="bed" method="get">
    <button type="submit">Beds</button>
</form>
</body>
</html>*/

    /*<!DOCTYPE html>   from catalog.html рабочая
<html xmlns:th="http://www.thymeleaf.org">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Catalog</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
<form action="/web-app/" method="get">
    <button type="submit">To home page</button>
</form>
    <h1>Product category</h1>
<hr>
<div th:if="${not #lists.isEmpty(categorydtos)}">
    <div th:each="categorydto : ${categorydtos}">
        <form id="category" method="get">
            <input name="id" type="text" th:value= "${categorydto.id}" id="id" hidden="true"/>
        </form>
        <p th:text="'Category: ' + ${categorydto.name}" />
        <div >
            <button type="submit" formaction="/web-app/category/products?name={categorydto.name}" >Products</button>
        </div>
        <hr>
    </div>
</div>
</body>
</html>*/

}


