package je.service.api;

import je.model.PurchaseOrder;

public interface PurchaseOrderService {

    PurchaseOrder findById(Long id);
}
