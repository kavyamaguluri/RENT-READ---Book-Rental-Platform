package com.crio.rent_read.repsitories;

import java.util.List;
import com.crio.rent_read.entities.Book;
import com.crio.rent_read.enums.AvailabilityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAvailabilityStatus(AvailabilityStatus status);
    
}
