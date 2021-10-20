package je.service;

import je.dao.DeliveryDAO;
import je.exception.NotFoundDataException;
import je.model.Delivery;
import je.service.api.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeliveryServiceImpl implements DeliveryService {

    private final DeliveryDAO deliveryDAO;

    @Override
    public Delivery findById(Long id) throws NotFoundDataException {
        final Delivery delivery = deliveryDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Delivery with id = " + id + " is not found"));
        return delivery;
    }
}
