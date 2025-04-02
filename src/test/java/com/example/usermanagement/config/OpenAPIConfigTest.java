package com.example.usermanagement.config;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

class OpenAPIConfigTest {

    @Test
    void myOpenAPI_ShouldReturnValidConfiguration() {
        // Arrange
        OpenAPIConfig config = new OpenAPIConfig();
        ReflectionTestUtils.setField(config, "serverPort", "8080");

        // Act
        OpenAPI result = config.myOpenAPI();

        // Assert
        assertNotNull(result);
        assertNotNull(result.getInfo());
        assertEquals("User Management API", result.getInfo().getTitle());
        assertEquals("1.0", result.getInfo().getVersion());
        assertFalse(result.getServers().isEmpty());
        assertEquals("http://localhost:8080", result.getServers().get(0).getUrl());
    }
}