package je.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import je.dto.DeliveryDTO;
import je.service.api.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DeliveryController {

    private final ObjectMapper objectMapper;
    private final DeliveryService deliveryService;

    @GetMapping(value = "/delivery")
    public DeliveryDTO getDelivery(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(deliveryService.findById(id), DeliveryDTO.class);
    }

}
