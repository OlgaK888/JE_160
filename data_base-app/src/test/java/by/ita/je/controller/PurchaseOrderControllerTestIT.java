package by.ita.je.controller;

import by.ita.je.exception.NotFoundDataException;
import by.ita.je.service.api.ProductService;
import by.ita.je.service.api.PurchaseOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class PurchaseOrderControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    @Test
    @SneakyThrows
    public void when_getPurchaseOrder_returnOK() {

        long id = 3L;

        mockMvc.perform(
                get("/order?id=" + id, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.sum").value("1500.0"));
    }

    @Test
    @SneakyThrows
    public void when_getPurchaseOrder_throwNotFoundDataException(){

        long id = 70L;

        mockMvc.perform(
                get("/order?id=" + id, id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundDataException))
                .andExpect(result ->assertEquals("Purchase order with id = " + id + " is not found",
                        result.getResolvedException().getMessage()));
    }
}
