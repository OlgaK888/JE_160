package by.ita.je.controller;

import by.ita.je.dto.ReviewDTO;
import by.ita.je.service.api.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ObjectMapper objectMapper;
    private final ReviewService reviewService;

    @GetMapping(value = "/review")
    public ReviewDTO getReview(@RequestParam(value = "id", required = true) Long id) {

        return objectMapper.convertValue(reviewService.findById(id), ReviewDTO.class);
    }
}
