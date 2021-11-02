package by.ita.je.controller;

import by.ita.je.model.ShoppingCart;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    @Test
    @SneakyThrows
    public void when_getCategoryByProduct_returnOK() {

        long id = 2L;

        mockMvc.perform(
                get("/category/by/product?id=" + id, id))
                .andExpect(status().isOk());
    }

}
