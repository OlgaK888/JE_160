package je.service;

import je.dao.CommentDAO;
import je.exception.NotFoundDataException;
import je.model.Comment;
import je.service.api.CommentService;
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
