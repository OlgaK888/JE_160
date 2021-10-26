package by.ita.je.service;

import by.ita.je.dao.DeliveryDAO;
import by.ita.je.model.Delivery;
import by.ita.je.service.api.DeliveryService;
import by.ita.je.exception.NotFoundDataException;
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
