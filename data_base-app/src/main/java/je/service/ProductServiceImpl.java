package je.service;

import je.dao.ProductDAO;
import je.exception.NotFoundDataException;
import je.model.Product;
import je.service.api.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
