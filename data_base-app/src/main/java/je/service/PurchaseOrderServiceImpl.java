package je.service;

import je.dao.PurchaseOrderDAO;
import je.exception.NotFoundDataException;
import je.model.PurchaseOrder;
import je.service.api.PurchaseOrderService;
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
