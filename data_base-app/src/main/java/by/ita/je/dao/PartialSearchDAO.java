package by.ita.je.dao;

import by.ita.je.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PartialSearchDAO extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE NAME LIKE '% %'")
    List<Product> findByPartialSearch();
}


