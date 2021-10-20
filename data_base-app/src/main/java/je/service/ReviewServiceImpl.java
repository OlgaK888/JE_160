package je.service;

import je.dao.ReviewDAO;
import je.exception.NotFoundDataException;
import je.model.Review;
import je.service.api.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewDAO reviewDAO;

    @Override
    public Review findById(Long id) throws NotFoundDataException {
        final Review review = reviewDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Review with id = " + id + " is not found"));
        return review;
    }
}
