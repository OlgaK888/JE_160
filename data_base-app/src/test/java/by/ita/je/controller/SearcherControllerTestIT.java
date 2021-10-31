package by.ita.je.controller;

import by.ita.je.dto.FilterByCategoryPriceRatingDTO;
import by.ita.je.dto.FilterByPartOfNameDTO;
import by.ita.je.model.Product;
import by.ita.je.service.api.SearcherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Collection;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class SearcherControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SearcherService searcherService;

    @Test
    @SneakyThrows
    public void when_findByPartOfName_returnOK() {

        final FilterByPartOfNameDTO filterByPartOfNameDTO = new FilterByPartOfNameDTO();
        filterByPartOfNameDTO.setPartOfName("1");

        Collection<Product> products = searcherService.findByPartOfName(filterByPartOfNameDTO.getPartOfName());

        mockMvc.perform(
                post("/search/partial")
                        .content(objectMapper.writeValueAsString(filterByPartOfNameDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    public void when_findByCategoryPriceRating_returnOK() {

        final FilterByCategoryPriceRatingDTO filterByCategoryPriceRatingDTO = new FilterByCategoryPriceRatingDTO();
        filterByCategoryPriceRatingDTO.setNameCategory("category for test");
        filterByCategoryPriceRatingDTO.setPriceFrom(BigDecimal.valueOf(1000));
        filterByCategoryPriceRatingDTO.setPriceTo(BigDecimal.valueOf(3000));
        filterByCategoryPriceRatingDTO.setRatingFrom(4.0);
        filterByCategoryPriceRatingDTO.setRatingTo(5.0);

        Collection<Product> products = searcherService.findByCategoryPriceRating("category for test",
                BigDecimal.valueOf(1000), BigDecimal.valueOf(3000), 4.0, 5.0);

        mockMvc.perform(
                post("/search/category/price/rating")
                        .content(objectMapper.writeValueAsString(filterByCategoryPriceRatingDTO))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(products)));
    }

}
