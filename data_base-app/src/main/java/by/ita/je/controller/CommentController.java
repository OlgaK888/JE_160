package by.ita.je.controller;

import by.ita.je.dto.CommentDTO;
import by.ita.je.service.api.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public CommentDTO getComment(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(commentService.findById(id), CommentDTO.class);
    }

}
