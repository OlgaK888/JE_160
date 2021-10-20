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
public class PaymentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String typeName;

    @OneToMany
    private Collection<Delivery> deliveries;
}
