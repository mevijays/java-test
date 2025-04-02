package com.example.usermanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.usermanagement.model.User;
import com.example.usermanagement.repository.UserRepository;
import com.example.usermanagement.exception.UserNotFoundException;

import java.util.Optional;

class UserServiceImplTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImpl userService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void whenUpdateUser_thenReturnUpdatedUser() {
        // Arrange
        User existingUser = new User();
        existingUser.setId(1L);
        existingUser.setName("Original Name");
        existingUser.setEmail("original@email.com");
        existingUser.setPhone("1234567890");
        existingUser.setActive(true);
        
        User updateData = new User();
        updateData.setId(1L);
        updateData.setName("Updated Name");
        updateData.setEmail("updated@email.com");
        
        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(any(User.class))).thenReturn(updateData);
        
        // Act
        User updatedUser = userService.updateUser(1L, updateData);
        
        // Assert
        assertNotNull(updatedUser);
        assertEquals("Updated Name", updatedUser.getName());
        assertEquals("updated@email.com", updatedUser.getEmail());
    }
    
    @Test
    void whenUpdateUserNotFound_thenThrowException() {
        // Arrange
        User updateData = new User();
        updateData.setId(999L);
        
        when(userRepository.findById(999L)).thenReturn(Optional.empty());
        
        // Act & Assert
        assertThrows(UserNotFoundException.class, () -> 
            userService.updateUser(999L, updateData)
        );
    }
}