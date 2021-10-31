package by.ita.je.dao;

import by.ita.je.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface SearcherDAO extends JpaRepository<Product, Long> {

    /*@Query("SELECT p FROM Product p WHERE p.rating BETWEEN :ratingFrom AND :ratingTo")
    Collection<Product> findByRating(double ratingFrom, double ratingTo);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceFrom AND :priceTo")
    Collection<Product> findByPrice(BigDecimal priceFrom, BigDecimal priceTo);

    @Query("SELECT p FROM Product p INNER JOIN p.category c WHERE c.name =:nameCategory")
    Collection<Product> findByCategory(String nameCategory);

    @Query("SELECT p FROM Product p INNER JOIN p.category c WHERE (p.price BETWEEN :priceFrom AND :priceTo)" +
            " AND (p.rating BETWEEN :ratingFrom AND :ratingTo) AND (c.name =:nameCategory)")
    Collection<Product> findByCategoryPriceRating(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                                  double ratingFrom, double ratingTo);*/

    /*@Query("SELECT p.name FROM Product p WHERE p.name LIKE CONCAT('%',:partOfName,'%')")
    Collection<Product> findByPartOfName(String partOfName);*/

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:partOfName,'%')")
    Collection<Product> findByPartOfName(String partOfName);
}