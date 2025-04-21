package com.crio.rent_read.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import com.crio.rent_read.entities.Book;
import com.crio.rent_read.entities.Rental;
import com.crio.rent_read.entities.User;
import com.crio.rent_read.enums.AvailabilityStatus;
import com.crio.rent_read.exceptions.RentalLimitExceededException;
import com.crio.rent_read.exceptions.ResourceNotFoundException;
import com.crio.rent_read.repsitories.RentalRepository;

@Service
public class RentalService {

    private static final int MAX_RENTALS_PER_USER = 2;

    @Autowired
    private RentalRepository rentalRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Transactional
    public Rental rentBook(Long userId, Long bookId) throws Exception {
        
        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);

        int activeRentals = rentalRepository.countByUser(user);
        if (activeRentals >= MAX_RENTALS_PER_USER) {
            throw new RentalLimitExceededException("User has already reached the rental limit!");
        }

        if (book.getAvailabilityStatus() != AvailabilityStatus.AVAILABLE) {
            throw new Exception("Book is not available for rent");
        }

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.setRentAt(LocalDate.now());

        bookService.updateBookAvailability(book, AvailabilityStatus.NOT_AVAILABLE);
        return rentalRepository.save(rental);
    }

    @Transactional
    public void returnBook(Long rentalId) {
        
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new ResourceNotFoundException("Rental not found with id: " + rentalId));

        if (rental.getReturnDate() != null) {
            throw new IllegalStateException("Book has already been returned");
        }

        rental.setReturnDate(LocalDate.now());
        rentalRepository.save(rental);

        Book book = rental.getBook();
        bookService.updateBookAvailability(book, AvailabilityStatus.AVAILABLE);
    }

    public List<Rental> getActiveRentalsForUser(Long userId) {
        
        User user = userService.getUserById(userId);
        return rentalRepository.findByUser(user);
    }
}
