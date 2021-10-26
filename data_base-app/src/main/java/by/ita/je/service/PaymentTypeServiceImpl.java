package by.ita.je.service;

import by.ita.je.dao.PaymentTypeDAO;
import by.ita.je.model.PaymentType;
import by.ita.je.service.api.PaymentTypeService;
import by.ita.je.exception.NotFoundDataException;
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
