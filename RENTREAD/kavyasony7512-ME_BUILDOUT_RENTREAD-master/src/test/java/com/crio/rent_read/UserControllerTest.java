package com.crio.rent_read;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import com.crio.rent_read.controllers.UserController;
import com.crio.rent_read.dto.UserResponse;
import com.crio.rent_read.entities.User;
import com.crio.rent_read.services.UserService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void testRegisterUser() throws Exception {
        // Create the User object with a Role enum
        User user = new User(1L, "John", "Doe", "john.doe@example.com", "password123", null);
        
        // Convert User to UserResponse
        UserResponse userResponse = UserResponse.fromUser(user);

        when(userService.register(any())).thenReturn(userResponse);
        
        mockMvc.perform(post("/auth/signup")
                .contentType("application/json")
                .content("{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }


    @Test
    void testLoginUser() throws Exception {
        UserResponse userResponse = new UserResponse(1L, "John", "Doe", "john.doe@example.com","Role.USER");
        when(userService.login(any())).thenReturn(userResponse);
        
        mockMvc.perform(post("/auth/login")
                .contentType("application/json")
                .content("{\"email\":\"john.doe@example.com\",\"password\":\"password123\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john.doe@example.com"));
    }
}
