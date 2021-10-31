package by.ita.je.controller;

import by.ita.je.dto.CategoryDTO;
import by.ita.je.dto.ProductDTO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Category;
import by.ita.je.model.Product;
import by.ita.je.service.api.PaymentTypeService;
import by.ita.je.service.api.ProductService;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class ProductControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ProductService productService;

    @Test
    @SneakyThrows
    public void when_getProduct_returnOK() {

        long id = 1L;

        mockMvc.perform(
                get("/product?id=" + id, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("test 1"));
    }

    @Test
    @SneakyThrows
    public void when_getProduct_throwNotFoundDataException(){

        long id = 70L;

        mockMvc.perform(
                get("/product?id=" + id, id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundDataException))
                .andExpect(result ->assertEquals("Product with id = " + id + " is not found",
                        result.getResolvedException().getMessage()));
    }

    @Test
    @SneakyThrows
    public void getAllProducts_returnOK()  {
        Collection<Product> products = productService.findAllProducts();
        Collection<ProductDTO> collect = products.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
        mockMvc.perform(
                get("/products"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(collect)));
    }
}
