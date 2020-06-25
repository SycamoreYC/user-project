package com.java.userproject.errors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

class GlobalExceptionHandlerTest {
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        this.globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void should_handleUserNotFoundException() {
        UserNotFoundException userNotFoundException = new UserNotFoundException("Error Message");

        ResponseEntity<ErrorResult> response = this.globalExceptionHandler.handle(userNotFoundException);

        assertThat(response.getBody().getError()).isEqualTo("User Not Found");
        assertThat(response.getBody().getMessage()).isEqualTo("Error Message");
        assertThat(response.getBody().getStatus()).isEqualTo(NOT_FOUND);
    }

    @Test
    void should_handleMethodArgumentNotValidException() {
        MethodArgumentNotValidException methodArgumentNotValidException = mock(MethodArgumentNotValidException.class);
        when(methodArgumentNotValidException.getMessage()).thenReturn("Error Message");

        ResponseEntity<ErrorResult> response = this.globalExceptionHandler.handle(methodArgumentNotValidException);

        assertThat(response.getBody().getError()).isEqualTo("Invalid parameter");
        assertThat(response.getBody().getMessage()).isEqualTo("Error Message");
        assertThat(response.getBody().getStatus()).isEqualTo(BAD_REQUEST);
    }

    @Test
    void should_handleGeneralException() {
        Throwable throwable = new Throwable("Test Error");

        ResponseEntity<ErrorResult> response = this.globalExceptionHandler.handle(throwable);

        assertThat(response.getBody().getMessage()).isEqualTo("Test Error");
        assertThat(response.getBody().getStatus()).isEqualTo(INTERNAL_SERVER_ERROR);
    }
}
