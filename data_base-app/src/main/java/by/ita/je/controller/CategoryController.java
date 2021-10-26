package by.ita.je.controller;

import by.ita.je.dto.CategoryDTO;
import by.ita.je.model.Category;
import by.ita.je.service.api.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final ObjectMapper objectMapper;
    private final CategoryService categoryService;

    @GetMapping(value = "/category")
    public CategoryDTO getCategory(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(categoryService.findById(id), CategoryDTO.class);
    }

    @GetMapping("/categories")
    public Collection<CategoryDTO> getAllCategories(){

        Collection<Category> categoryCollection = categoryService.findAllCategories();
        Collection<CategoryDTO> categoryDTOCollection = categoryCollection.stream()
                .map(category -> objectMapper.convertValue(category, CategoryDTO.class))
                .collect(Collectors.toList());

        return categoryDTOCollection;

    }
}
