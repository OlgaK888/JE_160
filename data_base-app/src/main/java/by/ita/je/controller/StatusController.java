package by.ita.je.controller;

import by.ita.je.dto.StatusDTO;
import by.ita.je.service.api.StatusService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatusController {

    private final ObjectMapper objectMapper;
    private final StatusService statusService;

    @GetMapping(value = "/status")
    public StatusDTO getStatus(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(statusService.findById(id), StatusDTO.class);
    }
}
