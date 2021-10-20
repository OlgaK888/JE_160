package je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private ShopDTO shop;
    private PaymentTypeDTO paymentType;

}
