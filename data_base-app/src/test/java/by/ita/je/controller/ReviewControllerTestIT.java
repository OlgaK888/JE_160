package by.ita.je.controller;

import by.ita.je.exception.NotFoundDataException;
import by.ita.je.service.api.PurchaseOrderService;
import by.ita.je.service.api.ReviewService;
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
public class ReviewControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ReviewService reviewService;

    @Test
    @SneakyThrows
    public void when_getReview_returnOK() {

        long id = 1L;

        mockMvc.perform(
                get("/review?id=" + id, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.message").value("This is good test service"));
    }

    @Test
    @SneakyThrows
    public void when_getReview_throwNotFoundDataException(){

        long id = 30L;

        mockMvc.perform(
                get("/review?id=" + id, id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundDataException))
                .andExpect(result ->assertEquals("Review with id = " + id + " is not found",
                        result.getResolvedException().getMessage()));
    }
}
