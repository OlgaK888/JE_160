package je.service.api;

import je.model.Product;

import java.math.BigDecimal;
import java.util.Collection;

public interface SearcherService {

    Collection<Product> findByRating(double ratingFrom, double ratingTo);

    Collection<Product> findByPrice(BigDecimal priceFrom, BigDecimal priceTo);

    Collection<Product> findByCategory(String nameCategory);
}
