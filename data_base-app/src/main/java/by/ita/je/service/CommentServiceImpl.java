package by.ita.je.service;

import by.ita.je.dao.CommentDAO;
import by.ita.je.model.Comment;
import by.ita.je.service.api.CommentService;
import by.ita.je.exception.NotFoundDataException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentDAO commentDAO;

    @Override
    public Comment findById(Long id) throws NotFoundDataException {
        final Comment comment = commentDAO.findById(id)
                .orElseThrow(() -> new NotFoundDataException("Comment with id = " + id + " is not found"));
        return comment;
    }
}
