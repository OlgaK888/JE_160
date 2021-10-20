package je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseOrderDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ZonedDateTime orderTime;
    private long customerId;
    private BigDecimal sum;
    private String status;
    private DeliveryDTO delivery;
    private List<ProductDTO> products;
    private List<StatusDTO> statuses;

}
