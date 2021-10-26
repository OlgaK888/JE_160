package by.ita.je.controller;

import by.ita.je.dto.PurchaseOrderDTO;
import by.ita.je.service.api.PurchaseOrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final ObjectMapper objectMapper;
    private final PurchaseOrderService purchaseOrderService;

    @GetMapping(value = "/order")
    public PurchaseOrderDTO getPurchaseOrder(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(purchaseOrderService.findById(id), PurchaseOrderDTO.class);
    }

}
