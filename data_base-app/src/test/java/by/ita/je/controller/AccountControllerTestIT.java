package by.ita.je.controller;

import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Account;
import by.ita.je.service.api.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.servlet.function.RequestPredicates.contentType;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class AccountControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    /*private Account accountForTesting() {

        Account account = getAccount();
        account.setName("Michail");
        account.setPhoneNumber(375291234567L);
        account.setCountry("Belarus");
        account.setEmail("michail@mail.ru");
        return account;
    }

    private Account getAccount() {
        return new Account();
    }*/

    @Test
    @SneakyThrows
    public void when_getAccount_returnOK() {

        long id = 2L;

        mockMvc.perform(
                get("/account?id=" + id, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Test 2"));
    }

    @Test
    @SneakyThrows
    public void when_getAccount_throwNotFoundDataException(){

        long id = 100L;

        mockMvc.perform(
                get("/account?id=" + id, id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundDataException))
                .andExpect(result ->assertEquals("Account with id = " + id + " is not found",
                        result.getResolvedException().getMessage()));
    }
}
