package je.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import je.dto.CommentDTO;
import je.service.api.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final ObjectMapper objectMapper;
    private final CommentService commentService;

    @GetMapping(value = "/comment")
    public CommentDTO getComment(@RequestParam(value = "id", required = false) Long id) {

        return objectMapper.convertValue(commentService.findById(id), CommentDTO.class);
    }

}
