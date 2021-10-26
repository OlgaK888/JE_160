package by.ita.je.controller;

import by.ita.je.dto.ShopDTO;
import by.ita.je.model.Shop;
import by.ita.je.service.api.ShopService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ShopController {

    private final ObjectMapper objectMapper;
    private final ShopService shopService;

    @GetMapping(value = "/shop")
    public ShopDTO getShop(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(shopService.findById(id), ShopDTO.class);
    }

    @GetMapping("/shops")
    public Collection<ShopDTO> getAllShops(){

        Collection<Shop> shopCollection = shopService.findAllShops();
        Collection<ShopDTO> shopDTOCollection = shopCollection.stream()
                .map(shop -> objectMapper.convertValue(shop, ShopDTO.class))
                .collect(Collectors.toList());

        return shopDTOCollection;

    }
}
