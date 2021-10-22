package je.dao;

import je.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.Collection;

public interface SearcherDao extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.rating BETWEEN :ratingFrom AND :ratingTo")
    Collection<Product> findByRating(double ratingFrom, double ratingTo);

    @Query("SELECT p FROM Product p WHERE p.price BETWEEN :priceFrom AND :priceTo")
    Collection<Product> findByPrice(BigDecimal priceFrom, BigDecimal priceTo);

    @Query("SELECT p FROM Product p INNER JOIN p.category c WHERE c.name =:nameCategory")
    Collection<Product> findByCategory(String nameCategory);
}
