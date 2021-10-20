package je.service.api;

import je.model.Comment;

public interface CommentService {

    Comment findById(Long id);
}
