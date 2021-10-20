package je.dto;

import je.model.Product;
import je.model.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long phoneNumber;
    private String country;
    private String email;
    private Collection<Product> ShoppingCart;
    private Collection<PurchaseOrder> orderToBy;
    private Collection<PurchaseOrder> orderToSell;

}