package by.ita.je.controller;

import by.ita.je.exception.NotFoundDataException;
import by.ita.je.service.api.ShoppingCartService;
import by.ita.je.service.api.StatusService;
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
public class StatusControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StatusService statusService;

    @Test
    @SneakyThrows
    public void when_getStatus_returnOK() {

        long id = 4L;

        mockMvc.perform(
                get("/status?id=" + id, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("test 4"));
    }

    @Test
    @SneakyThrows
    public void when_getStatus_throwNotFoundDataException(){

        long id = 80L;

        mockMvc.perform(
                get("/status?id=" + id, id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundDataException))
                .andExpect(result ->assertEquals("Status with id = " + id + " is not found",
                        result.getResolvedException().getMessage()));
    }
}
