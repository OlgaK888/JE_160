package by.ita.je.service;

import by.ita.je.dao.SearcherCriteriaDAO;
import by.ita.je.dao.SearcherDAO;
import by.ita.je.model.Product;
import by.ita.je.service.api.SearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;

@Repository
@RequiredArgsConstructor
public class SearcherServiceImpl implements SearcherService {

    private final SearcherDAO searcherDAO;
    private final SearcherCriteriaDAO searcherCriteriaDAO;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Collection<Product> findByPartOfName(String partOfName) {return searcherDAO.findByPartOfName(partOfName); }

    @Override
    @Transactional(rollbackFor=Exception.class)
    public Collection<Product> findByCategoryPriceRating(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                                         double ratingFrom, double ratingTo) {

        return searcherCriteriaDAO.findByCategoryPriceRating(nameCategory, priceFrom, priceTo, ratingFrom, ratingTo);
    }
}
