package by.ita.je.controller;

import by.ita.je.dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import by.ita.je.service.api.SearcherService;
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

    @PostMapping("/search/rating")
    public Collection<ProductDTO> findByRating(@RequestBody FilterByRatingDTO filterByRatingDTO) {
        return searcherService.findByRating(filterByRatingDTO.getRatingFrom(),
                filterByRatingDTO.getRatingTo()).stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/search/price")
    public Collection<ProductDTO> findByPrice(@RequestBody FilterByPriceDTO filterByPriceDTO) {
        return searcherService.findByPrice(filterByPriceDTO.getPriceFrom(),
                filterByPriceDTO.getPriceTo()).stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/search/category")
    public Collection<ProductDTO> findByCategory(@RequestBody FilterByCategoryPriceRatingDTO filterByCategoryDTO) {
        return searcherService.findByCategory(filterByCategoryDTO.getNameCategory()).stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    @PostMapping("/search/category/rating/price")
    public Collection<ProductDTO> findByCategoryPriceRating(
            @RequestBody FilterByCategoryPriceRatingDTO filterByCategoryPriceRatingDTO) {
        return searcherService.findByCategoryPriceRating(filterByCategoryPriceRatingDTO.getNameCategory(),
                filterByCategoryPriceRatingDTO.getPriceFrom(), filterByCategoryPriceRatingDTO.getPriceTo(),
                filterByCategoryPriceRatingDTO.getRatingFrom(), filterByCategoryPriceRatingDTO.getRatingTo()).stream()
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
