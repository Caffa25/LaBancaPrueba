package com.fibonacci.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FibonacciStatsTest {

    @Test
    void shouldInitializeWithGivenValues() {
        FibonacciStats stats = new FibonacciStats(5, 10);

        assertEquals(5, stats.getN());
        assertEquals(10, stats.getCount());
    }

    @Test
    void shouldUpdateN() {
        FibonacciStats stats = new FibonacciStats(3, 1);
        stats.setN(7);

        assertEquals(7, stats.getN());
    }

    @Test
    void shouldUpdateCount() {
        FibonacciStats stats = new FibonacciStats(3, 1);
        stats.setCount(5);

        assertEquals(5, stats.getCount());
    }

    @Test
    void shouldIncrementCount() {
        FibonacciStats stats = new FibonacciStats(3, 2);
        stats.incrementCount();

        assertEquals(3, stats.getCount());
    }

    @Test
    void shouldHaveNoArgsConstructor() {
        FibonacciStats stats = new FibonacciStats();
        assertNotNull(stats);
    }
}
