package je.service;

import je.dao.SearcherDao;
import je.model.Product;
import je.service.api.SearcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class SearcherServiceImpl implements SearcherService {

    private final SearcherDao searcherDao;

    @Override
    public Collection<Product> findByRating(double ratingFrom, double ratingTo) {
        return searcherDao.findByRating(ratingFrom, ratingTo);
    }

    @Override
    public Collection<Product> findByPrice(BigDecimal priceFrom, BigDecimal priceTo) {
        return searcherDao.findByPrice(priceFrom, priceTo);
    }

    @Override
    public Collection<Product> findByCategory(String nameCategory) {
        return searcherDao.findByCategory(nameCategory);
    }
}
