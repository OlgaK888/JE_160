package by.ita.je.service;

import by.ita.je.model.Product;
import by.ita.je.dao.SearcherDAO;
import by.ita.je.service.api.SearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearcherServiceImpl implements SearcherService {

    private final SearcherDAO searcherDAO;

    @Override
    public Collection<Product> findByRating(double ratingFrom, double ratingTo) {
        return searcherDAO.findByRating(ratingFrom, ratingTo);
    }

    @Override
    public Collection<Product> findByPrice(BigDecimal priceFrom, BigDecimal priceTo) {
        return searcherDAO.findByPrice(priceFrom, priceTo);
    }

    @Override
    public Collection<Product> findByCategory(String nameCategory) {
        return searcherDAO.findByCategory(nameCategory);
    }

    /*@Override
    public Collection<Product> findByPartOfName(String partOfName) {return searcherDAO.findByPartOfName(partOfName); }*/

    @Override
    public List<Product> findByPartOfName(String partOfName) {return searcherDAO.findByPartOfName(partOfName); }

    @Override
    public Collection<Product> findByCategoryPriceRating(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                                         double ratingFrom, double ratingTo) {

        return searcherDAO.findByCategoryPriceRating(nameCategory, priceFrom, priceTo, ratingFrom, ratingTo);
    }
}
