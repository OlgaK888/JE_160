package by.ita.je.service.api;

import by.ita.je.model.Product;

import java.math.BigDecimal;
import java.util.Collection;

public interface SearcherService {

    Collection<Product> findByPartOfName(String partOfName);

    Collection<Product> findByCategoryPriceRating(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                                  double ratingFrom, double ratingTo);
}
