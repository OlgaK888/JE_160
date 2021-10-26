package by.ita.je.service;

import by.ita.je.dao.ReviewDAO;
import by.ita.je.model.Review;
import by.ita.je.service.api.ReviewService;
import by.ita.je.exception.NotFoundDataException;
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
