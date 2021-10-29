package by.ita.je.dao.daoImpl;

import by.ita.je.dao.SearcherCriteriaDAO;
import by.ita.je.model.Category;
import by.ita.je.model.Category_;
import by.ita.je.model.Product;
import by.ita.je.model.Product_;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

@Repository
public class SearcherCriteriaDAOImpl implements SearcherCriteriaDAO {

    @PersistenceContext
    EntityManager entityManager;



    @Override
    public List<Product> findByCategoryPriceRating(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                                         double ratingFrom, double ratingTo) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Join<Product, Category> categoryJoin = productRoot.join(Product_.CATEGORY);
        criteriaQuery.select(productRoot);
        criteriaQuery.where(cb.and(cb.between(productRoot.get(Product_.PRICE), priceFrom, priceTo),
                (cb.and(cb.between(productRoot.get(Product_.RATING), ratingFrom, ratingTo),
                        cb.equal(categoryJoin.get(Category_.NAME), nameCategory)))));
        TypedQuery<Product> typedQuery = entityManager.createQuery(criteriaQuery);
        return typedQuery.getResultList();
    }

    /*@Override
    public List<Product> findByCategoryPriceRating(String nameCategory, BigDecimal priceFrom, BigDecimal priceTo,
                                                         double ratingFrom, double ratingTo) {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = cb.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        Join<Product, Category> categoryJoin = productRoot.join(Product_.CATEGORY);
        Predicate predicateForCategory
                = cb.equal(categoryJoin.get(Category_.NAME), nameCategory);
        Predicate predicateForPrice
                = cb.between(productRoot.get(Product_.PRICE), priceFrom, priceTo);
        Predicate predicateForRating
                = cb.between(productRoot.get(Product_.RATING), ratingFrom, ratingTo);
        Predicate predicateForCategoryPrice
                = cb.and(predicateForCategory, predicateForPrice);
        Predicate predicateForCategoryRating
                = cb.and(predicateForCategory, predicateForRating);
        Predicate predicateForPriceRating
                = cb.and(predicateForPrice, predicateForRating);
        Predicate predicateForCategoryPriceRating
                = cb.and(predicateForCategory, predicateForPrice, predicateForRating);
        if (nameCategory != "" && priceTo.compareTo(BigDecimal.ZERO) == 0 && ratingTo == 0.00)
            criteriaQuery.where(predicateForCategory);
        if (nameCategory == "" && priceTo.compareTo(BigDecimal.ZERO) > 0 && ratingTo == 0.00)
            criteriaQuery.where(predicateForPrice);
        if (nameCategory == "" && priceTo.compareTo(BigDecimal.ZERO) == 0 && ratingTo > 0.00)
            criteriaQuery.where(predicateForRating);
        if (nameCategory != "" && priceTo.compareTo(BigDecimal.ZERO) > 0 && ratingTo == 0.00)
            criteriaQuery.where(predicateForCategoryPrice);
        if (nameCategory != "" && priceTo.compareTo(BigDecimal.ZERO) == 0 && ratingTo > 0.00)
            criteriaQuery.where(predicateForCategoryRating);
        if (nameCategory == "" && priceTo.compareTo(BigDecimal.ZERO) > 0 && ratingTo > 0.00)
            criteriaQuery.where(predicateForPriceRating);
        if (nameCategory != "" && priceTo.compareTo(BigDecimal.ZERO) > 0 && ratingTo > 0.00)
            criteriaQuery.where(predicateForCategoryPriceRating);
        Predicate finalPredicate = cb.or(predicateForCategoryPriceRating,
                predicateForCategoryPrice, predicateForCategoryRating,
                predicateForPriceRating, predicateForCategory, predicateForPrice, predicateForRating);
        criteriaQuery.where(finalPredicate);
        List<Product> products = entityManager.createQuery(criteriaQuery).getResultList();
        return products;
    }*/
}


