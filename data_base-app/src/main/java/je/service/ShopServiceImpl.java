package je.service;

import je.dao.ShopDAO;
import je.exception.NotFoundDataException;
import je.model.Shop;
import je.service.api.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ShopServiceImpl implements ShopService {

    private final ShopDAO shopDAO;

    @Override
    public Shop findById(Long id) throws NotFoundDataException {
        final Shop shop = shopDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Shop with id = " + id + " is not found"));
        return shop;
    }
}
