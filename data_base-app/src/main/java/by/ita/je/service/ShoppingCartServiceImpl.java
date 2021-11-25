package by.ita.je.service;

import by.ita.je.dao.ShoppingCartDAO;
import by.ita.je.exception.NotCorrectDataException;
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
    public ShoppingCart create(ShoppingCart shoppingCart) throws NotCorrectDataException {

        try {

            final ShoppingCart shoppingCart1 = shoppingCartDAO.save(shoppingCart);
            return shoppingCart1;

        } catch (Exception e) {

            throw new NotCorrectDataException("Not correct data");
        }

    }

    @Override
    public ShoppingCart findById(Long id) {
        final ShoppingCart shoppingCart = shoppingCartDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Shopping cart with id = " + id + " is not found"));
        return shoppingCart;
    }

    @Transactional
    @Override
    public ShoppingCart update(Long id, ShoppingCart shoppingCart) {

        final ShoppingCart updatedShoppingCart = shoppingCartDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Shopping cart with id = " + id +
                        " is not found"));

        return shoppingCartDAO.save(updatedShoppingCart);
    }

}