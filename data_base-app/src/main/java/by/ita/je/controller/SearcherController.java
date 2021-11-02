package by.ita.je.controller;

import by.ita.je.dto.FilterByCategoryPriceRatingDTO;
import by.ita.je.dto.FilterByPartOfNameDTO;
import by.ita.je.dto.ProductDTO;
import by.ita.je.service.api.SearcherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class SearcherController {

    private final ObjectMapper objectMapper;
    private final SearcherService searcherService;

    @PostMapping("/search/category/price/rating")
    public Collection<ProductDTO> findByCategoryPriceRating(
            @RequestBody FilterByCategoryPriceRatingDTO filterByCategoryPriceRatingDTO) {
        return searcherService.findByCategoryPriceRating(
                filterByCategoryPriceRatingDTO.getNameCategory(),
                filterByCategoryPriceRatingDTO.getPriceFrom(),
                filterByCategoryPriceRatingDTO.getPriceTo(),
                filterByCategoryPriceRatingDTO.getRatingFrom(),
                filterByCategoryPriceRatingDTO.getRatingTo()).stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/search/partial")
    public Collection<ProductDTO> findByPartOfName(@RequestBody FilterByPartOfNameDTO filterByPartOfNameDTO) {
        return searcherService.findByPartOfName(filterByPartOfNameDTO.getPartOfName()).stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
