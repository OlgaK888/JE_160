package je.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private long phoneNumber;
    private  String address;
    private  String city;
    private int postalCode;
    private String country;
    private String email;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Collection<Product> ShoppingCart;*/

    @OneToOne(cascade = CascadeType.ALL)
    private ShoppingCart shoppingCart;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Collection<PurchaseOrder> orderToBy;*/

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Collection<PurchaseOrder> orderToSell;*/

}
