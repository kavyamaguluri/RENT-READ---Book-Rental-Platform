package com.crio.rent_read.repsitories;

import java.util.List;
import com.crio.rent_read.entities.Rental;
import com.crio.rent_read.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalRepository extends JpaRepository<Rental, Long> {
    List<Rental> findByUser(User user);
    int countByUser(User user);
}
