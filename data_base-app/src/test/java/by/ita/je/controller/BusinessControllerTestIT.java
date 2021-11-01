package by.ita.je.controller;

import by.ita.je.dto.CategoryDTO;
import by.ita.je.dto.ProductDTO;
import by.ita.je.dto.ShopDTO;
import by.ita.je.dto.ShoppingCartDTO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Category;
import by.ita.je.model.Product;
import by.ita.je.model.Shop;
import by.ita.je.model.ShoppingCart;
import by.ita.je.service.api.AccountService;
import by.ita.je.service.api.BusinessService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.stream.Collectors;

import static javax.swing.UIManager.put;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BusinessControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BusinessService businessService;


    @Test
    @SneakyThrows
    public void when_addProductToShoppingCart_returnOK() {

        long idCart = 1L;
        long idProduct = 1L;

        ShoppingCart shoppingCart = businessService.addProductToShoppingCart(idCart, idProduct);
        mockMvc.perform(
                MockMvcRequestBuilders.put("/product/to/shopping/cart/{idCart}/{idProduct}", idCart, idProduct))
                .andExpect(status().isOk());
    }

    /*@Test
    @SneakyThrows
    public void getAllProductsInShoppingCart_returnOK()  {

        long idCart = 1L;

        Collection<Product> products = businessService.findAllProductsInShoppingCart(idCart);
        Collection<ProductDTO> collect = products.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
        mockMvc.perform(
                get("/all/product/in/cart/{id_cart}"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(collect)));
    }*/

    /*@Test
    @SneakyThrows
    public void when_getProductsByCategory_returnOK() {

        long id = 2L;

        Collection<Product> productCollection = businessService.getProductsByCategory(id);
        Collection<ProductDTO> productDTOCollection = productCollection.stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
        mockMvc.perform(
                get("products/by/category?id=" + id, id))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(productDTOCollection)));
    }*/

    @Test
    @SneakyThrows
    public void when_getCategoryByProduct_returnOK() {

        long id = 2L;

        //Category category = businessService.getCategoryByProduct(id);
        mockMvc.perform(
                get("/category/by/product?id=" + id, id))
                .andExpect(status().isOk());
    }

}