package by.ita.je.service;

import by.ita.je.dao.PurchaseOrderDAO;
import by.ita.je.model.PurchaseOrder;
import by.ita.je.service.api.PurchaseOrderService;
import by.ita.je.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderDAO purchaseOrderDAO;

    @Override
    public PurchaseOrder findById(Long id) throws NotFoundDataException {
        final PurchaseOrder purchaseOrder = purchaseOrderDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Purchase order with id = " + id + " is not found"));
        return purchaseOrder;
    }
}
