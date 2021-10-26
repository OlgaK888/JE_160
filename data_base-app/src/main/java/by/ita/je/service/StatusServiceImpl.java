package by.ita.je.service;

import by.ita.je.dao.StatusDAO;
import by.ita.je.model.Status;
import by.ita.je.service.api.StatusService;
import by.ita.je.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatusServiceImpl implements StatusService {

    private final StatusDAO statusDAO;

    @Override
    public Status findById(Long id) throws NotFoundDataException {
        final Status status = statusDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Status with id = " + id + " is not found"));
        return status;
    }
}
