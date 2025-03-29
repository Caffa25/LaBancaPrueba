package com.fibonacci.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import jakarta.persistence.*;
import java.sql.SQLException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleDatabaseConnectionError(SQLException ex) {
        System.err.println("Database connection error: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Database connection error.");
    }

    @ExceptionHandler(PersistenceException.class)
    public ResponseEntity<String> handlePersistenceError(PersistenceException ex) {
        System.err.println("Persistence error: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Persistence error occurred while accessing the database.");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentError(IllegalArgumentException ex) {
        System.err.println("Illegal argument error: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Invalid input: " + ex.getMessage());
    }
}
