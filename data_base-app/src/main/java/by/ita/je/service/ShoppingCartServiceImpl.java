package by.ita.je.service;

import by.ita.je.dao.ShoppingCartDAO;
import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.ShoppingCart;
import by.ita.je.service.api.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartDAO shoppingCartDAO;

    @Transactional
    @Override
    public ShoppingCart create(ShoppingCart shoppingCart) {
        return shoppingCartDAO.save(shoppingCart);
    }

    @Override
    public ShoppingCart findById(Long id) {
        final ShoppingCart shoppingCart = shoppingCartDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Shopping cart with id = " + id + " is not found"));
        return shoppingCart;
    }

    @Transactional
    @Override
    public ShoppingCart update(ShoppingCart shoppingCart) {

        final ShoppingCart updateShoppingCart = shoppingCartDAO.save(shoppingCart);

        return updateShoppingCart;
    }
}