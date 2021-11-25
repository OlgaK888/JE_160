package by.ita.je.controller;

import by.ita.je.exception.NotFoundDataException;
import by.ita.je.model.Bookmarks;
import by.ita.je.service.api.BookmarksService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class BookmarksControllerTestIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private BookmarksService bookmarksService;

    @Test
    @SneakyThrows
    public void when_getBookmarks_returnOK() {

        long id = 3L;

        mockMvc.perform(
                get("/bookmarks?id=" + id, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id));
    }

    @Test
    @SneakyThrows
    public void when_getBookmarks_throwNotFoundDataException(){

        long id = 45L;

        mockMvc.perform(
                get("/bookmarks?id=" + id, id))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NotFoundDataException))
                .andExpect(result ->assertEquals("Bookmarks with id = " + id + " are not found",
                        result.getResolvedException().getMessage()));
    }

    @Test
    @SneakyThrows
    public void when_updateBookmarks_returnOK() {
        long id = 1L;
        Bookmarks bookmarks = new Bookmarks();
        bookmarks.setId(199);
        mockMvc.perform(
                put("/bookmarks?id=" + id, id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(199));
    }
}
