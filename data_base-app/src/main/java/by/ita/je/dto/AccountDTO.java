package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private ShoppingCartDTO shoppingCart;
    //private Collection<PurchaseOrder> orderToBy;
    //private Collection<PurchaseOrder> orderToSell;

}
