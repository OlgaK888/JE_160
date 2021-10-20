package je.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import je.dto.CategoryDTO;
import je.service.api.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final ObjectMapper objectMapper;
    private final CategoryService categoryService;

    @GetMapping(value = "/category")
    public CategoryDTO getCategory(@RequestParam(value = "id", required = false) Long id) {

        return objectMapper.convertValue(categoryService.findById(id), CategoryDTO.class);
    }
}
