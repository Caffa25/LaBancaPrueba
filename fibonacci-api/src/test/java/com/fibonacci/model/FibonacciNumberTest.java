package com.fibonacci.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class FibonacciNumberTest {

    @Test
    void shouldCreateFibonacciNumberWithConstructor() {
        FibonacciNumber fibonacci = new FibonacciNumber(7, 13);

        assertThat(fibonacci.getN()).isEqualTo(7);
        assertThat(fibonacci.getValue()).isEqualTo(13);
    }

    @Test
    void shouldSetAndGetValuesCorrectly() {
        FibonacciNumber fibonacci = new FibonacciNumber();
        fibonacci.setN(10);
        fibonacci.setValue(55);

        assertThat(fibonacci.getN()).isEqualTo(10);
        assertThat(fibonacci.getValue()).isEqualTo(55);
    }

    @Test
    void shouldVerifyEquality() {
        FibonacciNumber fib1 = new FibonacciNumber(5, 8);
        FibonacciNumber fib2 = new FibonacciNumber(6, 13);

        assertThat(fib1).isNotEqualTo(fib2);
    }

    @Test
    void shouldNotBeNull() {
        FibonacciNumber fibonacci = new FibonacciNumber(3, 2);
        assertThat(fibonacci).isNotNull();
        assertThat(fibonacci.getN()).isGreaterThan(0);
    }
}
