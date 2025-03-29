package com.fibonacci.business;

import com.fibonacci.data.FibonacciStatsRepository;
import com.fibonacci.model.FibonacciStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

class FibonacciStatsServiceTest {

    @Mock
    private FibonacciStatsRepository statsRepository;

    @InjectMocks
    private FibonacciStatsService statsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldIncrementCountWhenStatsExist() {
        int n = 5;
        FibonacciStats existingStats = new FibonacciStats(n, 3);

        when(statsRepository.findById((long) n)).thenReturn(Optional.of(existingStats));

        statsService.updateStats(n);

        verify(statsRepository, times(1)).findById((long) n);
        verify(statsRepository, times(1)).save(existingStats);
        assert(existingStats.getCount() == 4);
    }

    @Test
    void shouldCreateNewStatsWhenNotExist() {
        int n = 7;

        when(statsRepository.findById((long) n)).thenReturn(Optional.empty());

        statsService.updateStats(n);

        verify(statsRepository, times(1)).findById((long) n);
        verify(statsRepository, times(1)).save(any(FibonacciStats.class));
    }
}
