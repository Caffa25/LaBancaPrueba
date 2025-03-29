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

    @Mock
    private FibonacciStatsService statsService; 

    @InjectMocks
    private FibonacciService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnCachedFibonacciNumber() {
        FibonacciNumber cached = new FibonacciNumber(6, 8);
        when(repository.findById(6)).thenReturn(Optional.of(cached));

        long result = service.getFibonacci(6);

        assertEquals(8, result);
        verify(repository, times(1)).findById(6);
        verify(repository, never()).save(any());
        verify(statsService, times(1)).updateStats(6);
    }

    @Test
    void shouldCalculateAndStoreFibonacciWhenNotCached() {
        when(repository.findById(7)).thenReturn(Optional.empty());

        long result = service.getFibonacci(7);

        assertEquals(8, result); 
        verify(repository, times(1)).findById(7);
        verify(repository, times(1)).save(any(FibonacciNumber.class));
        verify(statsService, times(1)).updateStats(7);
    }

    @Test
    void shouldThrowExceptionForNegativeInput() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> service.getFibonacci(-5));

        assertEquals("n must be a positive integer", exception.getMessage());
        verify(repository, never()).findById(any());
        verify(repository, never()).save(any());
        verify(statsService, never()).updateStats(anyInt());
    }

    @Test
    void shouldReturnCorrectFibonacciForBaseCases() {
        assertEquals(0, service.getFibonacci(1));
        assertEquals(1, service.getFibonacci(2));

        verify(repository, never()).findById(any());
        verify(repository, never()).save(any());
        verify(statsService, never()).updateStats(anyInt());
    }

    @Test
    void shouldReturnCorrectFibonacciForLargerNumbers() {
        when(repository.findById(10)).thenReturn(Optional.empty());

        long result = service.getFibonacci(10); 

        assertEquals(34, result);
        verify(repository, times(1)).findById(10);
        verify(repository, times(1)).save(any(FibonacciNumber.class));
        verify(statsService, times(1)).updateStats(10);
    }

    @Test
    void shouldCacheFibonacciNumbers() {
        FibonacciNumber cached = new FibonacciNumber(9, 34);
        when(repository.findById(9)).thenReturn(Optional.of(cached));

        long firstCall = service.getFibonacci(9);
        long secondCall = service.getFibonacci(9);

        assertEquals(34, firstCall);
        assertEquals(34, secondCall);
        
        verify(repository, times(2)).findById(9);
        verify(repository, never()).save(any());
        verify(statsService, times(2)).updateStats(9);
    }
}
