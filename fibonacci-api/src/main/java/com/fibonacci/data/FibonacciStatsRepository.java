package com.fibonacci.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fibonacci.model.FibonacciStats;

@Repository
public interface FibonacciStatsRepository extends JpaRepository<FibonacciStats, Long> {
}
