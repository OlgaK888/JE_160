package je.service;

import je.dao.ShopDAO;
import je.exception.NotFoundDataException;
import je.model.Shop;
import je.service.api.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public Collection<Shop> findAllShops() {
        final Spliterator<Shop> result = shopDAO.findAll().spliterator();
        return StreamSupport
                .stream(result,false)
                .collect(Collectors.toList());
    }
}
