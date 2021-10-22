package je.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import je.dto.FilterByCategoryDTO;
import je.dto.FilterByPriceDTO;
import je.dto.FilterByRatingDTO;
import je.dto.ProductDTO;
import je.service.api.SearcherService;
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
    public Collection<ProductDTO> findByCategory(@RequestBody FilterByCategoryDTO filterByCategoryDTO) {
        return searcherService.findByCategory(filterByCategoryDTO.getNameCategory()).stream()
                .map(product -> objectMapper.convertValue(product, ProductDTO.class))
                .collect(Collectors.toList());
    }
}
