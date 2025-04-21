package com.crio.rent_read.repsitories;

import java.util.Optional;
import com.crio.rent_read.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional <User> findByEmail(String email); 
    boolean existsByEmail(String email);   
}
