package by.ita.je.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Collection;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ZonedDateTime orderTime;
    private BigDecimal sum;
    private String status;

    /*@ManyToOne
    private Account accountForBuy;*/

    /*@ManyToOne
    private Account accountForSell;*/

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @OneToOne
    @JsonIgnore
    private ShoppingCart shoppingCart;

    /*@ManyToMany(cascade ={CascadeType.PERSIST, CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH},
            fetch = FetchType.LAZY)
    @JoinTable(name = "purchase_order_product",
            joinColumns = @JoinColumn(name = "purchase_order_id")
            , inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Collection<Product> products;*/

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "purchaseOrder", fetch = FetchType.LAZY)
    private Collection<Status> statuses;

}
