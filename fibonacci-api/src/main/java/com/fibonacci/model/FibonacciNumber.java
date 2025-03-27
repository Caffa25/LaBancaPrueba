package com.fibonacci.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fibonacci_cache")
public class FibonacciNumber {
    @Id
    private int n;
    
    @Column(nullable = false)
    private long value;

    public FibonacciNumber() {}

    public FibonacciNumber(int n, long value) {
        this.n = n;
        this.value = value;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }
}
