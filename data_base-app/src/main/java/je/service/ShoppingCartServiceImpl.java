package je.service;

import je.dao.ShoppingCartDAO;
import je.exception.NotFoundDataException;
import je.model.ShoppingCart;
import je.service.api.ShoppingCartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    ShoppingCartDAO shoppingCartDAO;

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
    public ShoppingCart update(Long id, ShoppingCart shoppingCart) {

        /*final ShoppingCart updateShoppingCart = shoppingCartDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Shopping cart with id = " + id + " is not found"));
        updateShoppingCart.setProducts();

        }*/


        return null;
    }
}