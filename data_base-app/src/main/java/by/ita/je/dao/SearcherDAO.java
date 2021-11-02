package by.ita.je.dao;

import by.ita.je.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface SearcherDAO extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name LIKE CONCAT('%',:partOfName,'%')")
    Collection<Product> findByPartOfName(String partOfName);
}