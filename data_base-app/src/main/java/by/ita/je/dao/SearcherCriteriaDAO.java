package by.ita.je.dao;

import by.ita.je.model.Product;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

public interface SearcherCriteriaDAO {

    List<Product> findByCategoryPriceRating(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                            double ratingFrom, double ratingTo);
}
