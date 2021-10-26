package by.ita.je.controller;

import by.ita.je.dto.ProductDTO;
import by.ita.je.model.Product;
import by.ita.je.service.api.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ObjectMapper objectMapper;
    private final ProductService productService;

    @GetMapping(value = "/product")
    public ProductDTO getProduct(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(productService.findById(id), ProductDTO.class);
    }

    @GetMapping("/products")
    public Collection<ProductDTO> getAllProducts(){

        Collection<Product> productCollection = productService.findAllProducts();
        Collection<ProductDTO> productDTOCollection = productCollection.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());

        return productDTOCollection;

    }
}
