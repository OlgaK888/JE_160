package by.ita.je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilterByCategoryPriceRatingDTO {

    private String nameCategory;
    private BigDecimal priceFrom;
    private BigDecimal priceTo;
    private double ratingFrom;
    private double ratingTo;
}
