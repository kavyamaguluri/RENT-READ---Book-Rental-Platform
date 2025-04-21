package com.crio.rent_read.services;

import jakarta.transaction.Transactional;
import com.crio.rent_read.dto.LoginRequest;
import com.crio.rent_read.dto.SignupRequest;
import com.crio.rent_read.dto.UserResponse;
import com.crio.rent_read.entities.User;
import com.crio.rent_read.enums.Role;
import com.crio.rent_read.exceptions.ResourceNotFoundException;
import com.crio.rent_read.repsitories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public UserResponse register(SignupRequest request){

        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Email already taken");
    
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        if(request.getRole() != null){
            user.setRole(request.getRole());
        }
        else{
            user.setRole(Role.USER);
        }

        User savedUser = userRepository.save(user);
        return UserResponse.fromUser(savedUser);

    }

    public UserResponse login(LoginRequest request){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + request.getEmail()));

        return UserResponse.fromUser(user);
        
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
    }
    
}
