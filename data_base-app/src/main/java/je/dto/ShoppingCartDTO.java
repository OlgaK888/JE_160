package je.dto;

import je.model.Account;
import je.model.Product;
import je.model.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShoppingCartDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Collection<ProductDTO> products;
    private PurchaseOrderDTO purchaseOrder;

}
