package com.example.usermanagement.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserGettersAndSetters() {
        // Arrange
        User user = new User();
        
        // Act
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPhone("1234567890");
        user.setActive(true);

        // Assert
        assertEquals(1L, user.getId());
        assertEquals("John Doe", user.getName());
        assertEquals("john@example.com", user.getEmail());
        assertEquals("1234567890", user.getPhone());
        assertTrue(user.isActive());
    }

    @Test
    void testUserConstructor() {
        // Act
        User user = new User();

        // Assert
        assertNull(user.getId());
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        assertTrue(user.isActive()); // Default value should be true
    }

    @Test
    void testUserEqualsAndHashCode() {
        // Arrange
        User user1 = new User();
        user1.setId(1L);
        user1.setName("John");
        user1.setEmail("test@example.com");
        user1.setPhone("1234567890");

        User user2 = new User();
        user2.setId(1L);
        user2.setName("John");
        user2.setEmail("test@example.com");
        user2.setPhone("1234567890");

        User user3 = new User();
        user3.setId(2L);
        user3.setName("Jane");
        user3.setEmail("other@example.com");
        user3.setPhone("9876543210");

        // Test equality with same object
        assertTrue(user1.equals(user1));
        
        // Test equality with null
        assertFalse(user1.equals(null));
        
        // Test equality with different class
        assertFalse(user1.equals(new Object()));
        
        // Test equality with same values
        assertTrue(user1.equals(user2));
        assertEquals(user1.hashCode(), user2.hashCode());
        
        // Test equality with different values
        assertFalse(user1.equals(user3));
        assertNotEquals(user1.hashCode(), user3.hashCode());
        
        // Test equality with null fields
        user2.setName(null);
        assertFalse(user1.equals(user2));
        user1.setName(null);
        assertTrue(user1.equals(user2));
        
        user2.setEmail(null);
        assertFalse(user1.equals(user2));
        user1.setEmail(null);
        assertTrue(user1.equals(user2));
        
        user2.setPhone(null);
        assertFalse(user1.equals(user2));
        user1.setPhone(null);
        assertTrue(user1.equals(user2));
    }

    @Test
    void testUserToString() {
        // Arrange
        User user = new User();
        user.setId(1L);
        user.setName("John Doe");
        user.setEmail("john@example.com");
        user.setPhone("1234567890");
        user.setActive(true);

        // Act
        String toString = user.toString();

        // Assert
        assertTrue(toString.contains("id=1"));
        assertTrue(toString.contains("name=John Doe"));
        assertTrue(toString.contains("email=john@example.com"));
        assertTrue(toString.contains("phone=1234567890"));
        assertTrue(toString.contains("active=true"));
    }
    
    @Test
    void testUserWithNullValues() {
        // Arrange
        User user = new User();
        
        // Test setting null values
        user.setName(null);
        user.setEmail(null);
        user.setPhone(null);
        
        // Assert null values
        assertNull(user.getName());
        assertNull(user.getEmail());
        assertNull(user.getPhone());
        
        // Test toString with null values
        String result = user.toString();
        assertTrue(result.contains("name=null"));
        assertTrue(result.contains("email=null"));
        assertTrue(result.contains("phone=null"));
    }

    @Test
    void testEqualsWithNullId() {
        User user1 = new User();
        user1.setName("Test");
        
        User user2 = new User();
        user2.setName("Test");
        
        // Both null IDs should be equal if other fields match
        assertTrue(user1.equals(user2));
        
        // Only one null ID should not be equal
        user2.setId(1L);
        assertFalse(user1.equals(user2));
    }

    @Test
    void testHashCodeConsistency() {
        User user = new User();
        user.setId(1L);
        user.setName("Test");
        user.setEmail("test@email.com");
        
        // Hash code should be consistent
        int hash1 = user.hashCode();
        int hash2 = user.hashCode();
        assertEquals(hash1, hash2);
        
        // Hash code should change when fields change
        user.setName("Different");
        assertNotEquals(hash1, user.hashCode());
    }
}