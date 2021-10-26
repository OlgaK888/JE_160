package by.ita.je.service;

import by.ita.je.dao.ProductDAO;
import by.ita.je.model.Product;
import by.ita.je.service.api.ProductService;
import by.ita.je.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDAO productDAO;

    @Override
    public Product findById(Long id) throws NotFoundDataException {
        final Product product = productDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Product with id = " + id + " is not found"));
        return product;
    }

    @Override
    public Collection<Product> findAllProducts() {
        final Spliterator<Product> result = productDAO.findAll().spliterator();
        return StreamSupport
                .stream(result,false)
                .collect(Collectors.toList());
    }
}
