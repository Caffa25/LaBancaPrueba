package com.fibonacci.model;

import jakarta.persistence.*;

@Entity
@Table(name = "fibonacci_stats")
public class FibonacciStats {

    @Id
    private int n;

    @Column(nullable = false)
    private int count;

    public FibonacciStats() {}

    public FibonacciStats(int n, int count) {
        this.n = n;
        this.count = count;
    }

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void incrementCount() {
        this.count++;
    }
}
