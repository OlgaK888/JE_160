package je.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import je.dto.ProductDTO;
import je.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ObjectMapper objectMapper;
    private final ProductService productService;

    @GetMapping(value = "/product")
    public ProductDTO getProduct(@RequestParam(value = "id", required = false) Long id) {

        return objectMapper.convertValue(productService.findById(id), ProductDTO.class);
    }
}
