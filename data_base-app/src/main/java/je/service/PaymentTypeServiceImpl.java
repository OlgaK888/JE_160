package je.service;

import je.dao.PaymentTypeDAO;
import je.exception.NotFoundDataException;
import je.model.PaymentType;
import je.service.api.PaymentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentTypeServiceImpl implements PaymentTypeService {

    private final PaymentTypeDAO paymentTypeDAO;

    @Override
    public PaymentType findById(Long id) throws NotFoundDataException {
        final PaymentType paymentType = paymentTypeDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Payment type with id = " + id + " is not found"));
        return paymentType;
    }
}
