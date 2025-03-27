package com.fibonacci.business;

import com.fibonacci.data.FibonacciRepository;
import com.fibonacci.model.FibonacciNumber;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class FibonacciServiceTest {

    @Mock
    private FibonacciRepository repository;

    @InjectMocks
    private FibonacciService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCachedFibonacciNumber() {
        FibonacciNumber cached = new FibonacciNumber(6, 5);
        when(repository.findById(6)).thenReturn(Optional.of(cached));

        long result = service.getFibonacci(6);

        assertEquals(5, result);
        verify(repository, times(1)).findById(6);
        verify(repository, never()).save(any());
    }

    @Test
    void shouldCalculateAndStoreFibonacciWhenNotCached() {
        when(repository.findById(7)).thenReturn(Optional.empty());

        long result = service.getFibonacci(7);

        assertEquals(8, result);
        verify(repository, times(1)).findById(7);
        verify(repository, times(1)).save(any(FibonacciNumber.class));

    }

    @Test
    void shouldThrowExceptionForNegativeInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.getFibonacci(-5));

        assertEquals("n must be a positive integer", exception.getMessage());
        verify(repository, never()).findById(any());
        verify(repository, never()).save(any());
    }

    @Test
    void shouldReturnCorrectFibonacciForBaseCases() {
        assertEquals(0, service.getFibonacci(1));
        assertEquals(1, service.getFibonacci(2));

        verify(repository, never()).findById(any());
        verify(repository, never()).save(any());
    }
}