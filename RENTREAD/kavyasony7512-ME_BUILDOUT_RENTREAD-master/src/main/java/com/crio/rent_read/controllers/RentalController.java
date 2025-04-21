package com.crio.rent_read.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.crio.rent_read.entities.Rental;
import com.crio.rent_read.services.RentalService;

@RestController
@RequestMapping("/rentals")
public class RentalController {
    @Autowired
    private RentalService rentalService;

    @PostMapping("/users/{userId}/books/{bookId}")
    public ResponseEntity<Rental> rentBook(
            @PathVariable Long userId,
            @PathVariable Long bookId) throws Exception {
        Rental rental = rentalService.rentBook(userId, bookId);
        return new ResponseEntity<>(rental, HttpStatus.CREATED);
    }

    @PutMapping("/{rentalId}")
    public ResponseEntity<Void> returnBook(@PathVariable Long rentalId) {
        rentalService.returnBook(rentalId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/active-rentals/users/{userId}")
    public ResponseEntity<List<Rental>> getActiveRentalsForUser(@PathVariable Long userId){
        List<Rental> activeRentals = rentalService.getActiveRentalsForUser(userId);
        return ResponseEntity.ok(activeRentals);
    }
}