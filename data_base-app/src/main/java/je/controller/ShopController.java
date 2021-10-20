package je.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import je.dto.ShopDTO;
import je.service.api.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ObjectMapper objectMapper;
    private final ShopService shopService;

    @GetMapping(value = "/shop")
    public ShopDTO getShop(@RequestParam(value = "id", required = false) Long id) {

        return objectMapper.convertValue(shopService.findById(id), ShopDTO.class);
    }
}
