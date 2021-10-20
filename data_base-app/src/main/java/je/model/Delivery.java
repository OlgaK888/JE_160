package je.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private PurchaseOrder purchaseOrder;

    @OneToOne(cascade = CascadeType.ALL)
    private Shop shop;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "payment_type_id")
    private PaymentType paymentType;
}
