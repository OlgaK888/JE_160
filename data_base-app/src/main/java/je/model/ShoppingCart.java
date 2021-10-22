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
public class ShoppingCart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToMany(cascade ={CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "shopping_cart_product",
            joinColumns = @JoinColumn(name = "shopping_cart_id")
            , inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Collection<Product> products;

    @OneToOne
    private Account account;

    @OneToOne(cascade = CascadeType.ALL)
    private PurchaseOrder purchaseOrder;

    /*@OneToOne(cascade = CascadeType.ALL)
    private PurchaseOrder purchaseOrder;*/

    /*@OneToOne(cascade = CascadeType.ALL)
    private PurchaseOrder purchaseOrder;*/

}
