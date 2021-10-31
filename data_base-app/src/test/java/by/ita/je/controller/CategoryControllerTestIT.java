package by.ita.je.controller;

import by.ita.je.dto.CategoryDTO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Category;
import by.ita.je.service.api.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CategoryControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CategoryService categoryService;

    @Test
    @SneakyThrows
    public void when_getCategory_returnOK() {

        long id = 2L;

        mockMvc.perform(
                get("/category?id=" + id, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("test2"));
    }

    @Test
    @SneakyThrows
    public void  when_getCategory_throwNotFoundDataException(){

        long id = 50L;

        mockMvc.perform(
                get("/category?id=" + id, id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundDataException))
                .andExpect(result ->assertEquals("Category with id = " + id + " is not found",
                        result.getResolvedException().getMessage()));
    }


    @Test
    @SneakyThrows
    public void getAllCategories_returnOK()  {
        Collection<Category> categories = categoryService.findAllCategories();
        Collection<CategoryDTO> collect = categories.stream()
                .map(category -> objectMapper.convertValue(category, CategoryDTO.class))
                .collect(Collectors.toList());
        mockMvc.perform(
                get("/categories"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(collect)));
    }
}
