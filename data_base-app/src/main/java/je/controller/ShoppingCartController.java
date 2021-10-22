package je.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import je.dao.ShoppingCartDAO;
import je.dto.ShopDTO;
import je.dto.ShoppingCartDTO;
import je.model.ShoppingCart;
import je.service.api.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ShoppingCartController {

    private final ObjectMapper objectMapper;
    private final ShoppingCartService shoppingCartService;

    @PostMapping(value = "/cart")
    public ShoppingCartDTO addNewShoppingCart(@RequestBody ShoppingCartDTO shoppingCartDTO) {

        final ShoppingCart shoppingCart = objectMapper.convertValue(shoppingCartDTO, ShoppingCart.class);

        return objectMapper.convertValue(shoppingCartService.create(shoppingCart), ShoppingCartDTO.class);
    }

    @GetMapping(value = "/cart")
    public ShoppingCartDTO getShoppingCart(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(shoppingCartService.findById(id), ShoppingCartDTO.class);
    }

    @PutMapping(value = "/cart")
    public ShoppingCartDTO updateShoppingCart(@RequestParam(value = "id", required = true) Long id,
                                                    @RequestBody ShoppingCartDTO shoppingCartDTO) {

        final ShoppingCart shoppingCart = objectMapper.convertValue(shoppingCartDTO, ShoppingCart.class);

        return objectMapper.convertValue(shoppingCartService.update(id, shoppingCart), ShoppingCartDTO.class);
    }
}
