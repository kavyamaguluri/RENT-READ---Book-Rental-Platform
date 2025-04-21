package com.crio.rent_read.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.crio.rent_read.entities.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;

    public static UserResponse fromUser(User user){
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setRole(user.getRole().name());
        return response;
    }
}
