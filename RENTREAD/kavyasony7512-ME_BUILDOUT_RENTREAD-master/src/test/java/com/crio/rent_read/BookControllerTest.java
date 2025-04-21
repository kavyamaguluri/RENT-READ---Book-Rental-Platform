package com.crio.rent_read;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import com.crio.rent_read.controllers.BookingController;
import com.crio.rent_read.entities.Book;
import com.crio.rent_read.services.BookService;

@WebMvcTest(BookingController.class)
class BookingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Test
    void testCreateBook() throws Exception {
        Book newBook = new Book();
        newBook.setId(2L);
        newBook.setTitle("Book 2");
        newBook.setAuthor("Author 2");
        newBook.setGenre("Genre 2");

        when(bookService.createBook(Mockito.any(Book.class))).thenReturn(newBook);
        
        mockMvc.perform(post("/books")
                .contentType("application/json")
                .content("{\"title\":\"Book 2\",\"author\":\"Author 2\",\"genre\":\"Genre 2\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("Book 2"));
    }
}