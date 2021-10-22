package je.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FilterByRatingDTO {

    private double ratingFrom;
    private double ratingTo;
}
