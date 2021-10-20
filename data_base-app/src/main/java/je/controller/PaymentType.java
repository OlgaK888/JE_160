package je.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import je.dto.PaymentTypeDTO;
import je.service.api.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentType {

    private final ObjectMapper objectMapper;
    private final PaymentTypeService paymentTypeService;

    @GetMapping(value = "/payment/type")
    public PaymentTypeDTO getPaymentType(@RequestParam(value = "id", required = false) Long id) {

        return objectMapper.convertValue(paymentTypeService.findById(id), PaymentTypeDTO.class);
    }

}
