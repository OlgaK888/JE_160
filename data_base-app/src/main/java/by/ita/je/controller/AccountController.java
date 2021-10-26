package by.ita.je.controller;

import by.ita.je.dto.AccountDTO;
import by.ita.je.service.api.AccountService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final ObjectMapper objectMapper;
    private final AccountService accountService;

    @GetMapping(value = "/account")
    public AccountDTO getAccount(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(accountService.findById(id), AccountDTO.class);
    }

}
