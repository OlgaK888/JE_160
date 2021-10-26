package by.ita.je.service;

import by.ita.je.dto.CategoryDTO;
import by.ita.je.dto.FilterByPartOfNameDTO;
import by.ita.je.dto.ProductDTO;
import by.ita.je.dto.ShopDTO;
import by.ita.je.service.api.WebAppService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WebAppServiceImpl implements WebAppService {

    private final RestTemplate restTemplate;
    private final String url="http://localhost:8003/data_base-app/";

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

   /* @Override
    public Collection<ProductDTO> getAllProductsByCategory(String categoryName) {
        ResponseEntity<ProductDTO[]> responseEntity = restTemplate.getForEntity(url
                        + "products/by/category/{categoryName}", ProductDTO[].class);
        List<ProductDTO> list = List.of(responseEntity.getBody());
        return list;
    }*/

    @Override
    public Collection<ProductDTO> findAllProductsByCategory(String id) {
        ResponseEntity<ProductDTO[]> responseEntity = restTemplate.getForEntity(url
                + "products/by/category/{id}", ProductDTO[].class);
        List<ProductDTO> list = List.of(responseEntity.getBody());
        return list;
    }

    /*@Override
    public Collection<ProductDTO> getProductsByCategory(String name) {
        ProductDTO[] productDTO = restTemplate.getForObject(url + "products/by/category/{name}",
                ProductDTO[].class);
        List<ProductDTO> list = List.of(productDTO);
        return list;
    }*/

    @Override
    public ProductDTO getProduct(Long id) {
        ProductDTO productDTO = restTemplate.getForObject(url +"product?id=" + id,
                ProductDTO.class);
        return productDTO;
    }

    @Override
    public List<ProductDTO> findByPartOfName(String partOfName) {
        FilterByPartOfNameDTO filteredDto = new FilterByPartOfNameDTO(partOfName);
        //List<ProductDTO> list = null;
        //if (filteredDto.getPartOfName() != "") {
            ResponseEntity<ProductDTO[]> responseEntity = restTemplate.postForEntity(url + "search/partial",
                    filteredDto, ProductDTO[].class);
        List<ProductDTO> list = List.of(responseEntity.getBody());
            //list = Arrays.asList(responseEntity.getBody());
        //}
        return list;
    }

}
