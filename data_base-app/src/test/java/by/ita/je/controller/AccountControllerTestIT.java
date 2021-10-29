package by.ita.je.controller;

import by.ita.je.model.Account;
import by.ita.je.service.api.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
//@WebMvcTest(AccountController.class)
@AutoConfigureMockMvc
public class AccountControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AccountService accountService;

    private Account accountForTesting() {

        Account account = getAccount();
        account.setName("Michail");
        account.setPhoneNumber(375291234567L);
        account.setCountry("Belarus");
        account.setEmail("michail@mail.ru");
        return account;
    }

    private Account getAccount() {
        return new Account();
    }

    /*@Test
    public void whenFindById_returnAccount() throws Exception{

        long id = accountForTesting().getId();

        mockMvc.perform(                            //MockMvcRequestBuilders
                get("/account?id=" + id, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Michail"));
                //.contentType(MediaType.APPLICATION_JSON))
                //.andExpect(MockMvcResultMatchers.jsonPath("accountId").value(id));
    }*/

}
