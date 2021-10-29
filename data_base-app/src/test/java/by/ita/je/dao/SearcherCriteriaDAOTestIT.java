package by.ita.je.dao;

import by.ita.je.model.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SearcherCriteriaDAOTestIT {

    @Autowired
    private SearcherCriteriaDAO searcherCriteriaDAO;

    String nameCategory = "category for test";
    BigDecimal priceFrom = BigDecimal.valueOf(1000);
    BigDecimal priceTo = BigDecimal.valueOf(3000);
    double ratingFrom = 4.0;
    double ratingTo = 5.0;

    @Test
    public void givenSearcherCriteriaDao_findByCriteria_thenOK() {
        List<Product> foundProduct = searcherCriteriaDAO
                .findByCategoryPriceRating(nameCategory, priceFrom, priceTo, ratingFrom, ratingTo);

        Assertions.assertNotNull(foundProduct);
        Assertions.assertEquals(1, foundProduct.size());
        Assertions.assertEquals("test 1", foundProduct.get(0).getName());
        Assertions.assertEquals("Good test", foundProduct.get(0).getDescription());
        Assertions.assertEquals(4.5, foundProduct.get(0).getRating());

    }
}
