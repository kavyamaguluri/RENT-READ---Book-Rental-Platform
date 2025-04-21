package com.crio.rent_read;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.time.LocalDate;
import com.crio.rent_read.controllers.RentalController;
import com.crio.rent_read.entities.Book;
import com.crio.rent_read.entities.Rental;
import com.crio.rent_read.entities.User;
import com.crio.rent_read.services.RentalService;


public class RentalControllerTest {

    private MockMvc mockMvc;

    @Mock
    private RentalService rentalService;

    @InjectMocks
    private RentalController rentalController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(rentalController).build();
    }

    @Test
    void testRentBook() throws Exception {
        Rental rental = new Rental(1L, new User(), new Book(), LocalDate.now(), null);
        when(rentalService.rentBook(1L, 1L)).thenReturn(rental);
        
        mockMvc.perform(post("/rentals/users/1/books/1"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void testReturnBook() throws Exception {
        doNothing().when(rentalService).returnBook(1L);
        
        mockMvc.perform(put("/rentals/1"))
                .andExpect(status().isNoContent());
    }
}
