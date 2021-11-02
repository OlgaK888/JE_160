package by.ita.je.service;

import by.ita.je.dao.SearcherCriteriaDAO;
import by.ita.je.model.Product;
import by.ita.je.dao.SearcherDAO;
import by.ita.je.service.api.SearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SearcherServiceImpl implements SearcherService {

    private final SearcherDAO searcherDAO;
    private final SearcherCriteriaDAO searcherCriteriaDAO;

    @Override
    public Collection<Product> findByPartOfName(String partOfName) {return searcherDAO.findByPartOfName(partOfName); }

    @Override
    public Collection<Product> findByCategoryPriceRating(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                                         double ratingFrom, double ratingTo) {

        return searcherCriteriaDAO.findByCategoryPriceRating(nameCategory, priceFrom, priceTo, ratingFrom, ratingTo);
    }
}
