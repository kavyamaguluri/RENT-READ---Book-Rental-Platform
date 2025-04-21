package com.crio.rent_read.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import com.crio.rent_read.entities.Book;
import com.crio.rent_read.enums.AvailabilityStatus;
import com.crio.rent_read.exceptions.ResourceNotFoundException;
import com.crio.rent_read.repsitories.BookRepository;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = getBookById(id);
        
        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setGenre(bookDetails.getGenre());
        book.setAvailabilityStatus(bookDetails.getAvailabilityStatus());
        
        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        Book book = getBookById(id);
        bookRepository.delete(book);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailabilityStatus(AvailabilityStatus.AVAILABLE);
    }

    public void updateBookAvailability(Book book, AvailabilityStatus status) {
        book.setAvailabilityStatus(status);
        bookRepository.save(book);
    }
}