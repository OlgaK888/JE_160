package by.ita.je.controller;

import by.ita.je.dto.PaymentTypeDTO;
import by.ita.je.service.api.PaymentTypeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentTypeController {

    private final ObjectMapper objectMapper;
    private final PaymentTypeService paymentTypeService;

    @GetMapping(value = "/payment/type")
    public PaymentTypeDTO getPaymentType(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(paymentTypeService.findById(id), PaymentTypeDTO.class);
    }

}
