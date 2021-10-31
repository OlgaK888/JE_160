package by.ita.je.controller;

import by.ita.je.dto.ProductDTO;
import by.ita.je.dto.ShopDTO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Product;
import by.ita.je.model.Shop;
import by.ita.je.service.api.ReviewService;
import by.ita.je.service.api.ShopService;
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
public class ShopControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ShopService shopService;

    @Test
    @SneakyThrows
    public void when_getShop_returnOK() {

        long id = 2L;

        mockMvc.perform(
                get("/shop?id=" + id, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Test shop 2"));
    }

    @Test
    @SneakyThrows
    public void when_getShop_throwNotFoundDataException(){

        long id = 37L;

        mockMvc.perform(
                get("/shop?id=" + id, id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundDataException))
                .andExpect(result ->assertEquals("Shop with id = " + id + " is not found",
                        result.getResolvedException().getMessage()));
    }

    @Test
    @SneakyThrows
    public void getAllShops_returnOK()  {
        Collection<Shop> shops = shopService.findAllShops();
        Collection<ShopDTO> collect = shops.stream()
                .map(shop -> objectMapper.convertValue(shop, ShopDTO.class))
                .collect(Collectors.toList());
        mockMvc.perform(
                get("/shops"))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(collect)));
    }
}
