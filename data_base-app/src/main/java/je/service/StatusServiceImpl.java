package je.service;

import je.dao.StatusDAO;
import je.exception.NotFoundDataException;
import je.model.Status;
import je.service.api.StatusService;
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
