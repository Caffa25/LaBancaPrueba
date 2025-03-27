package com.fibonacci.data;

import com.fibonacci.model.FibonacciNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FibonacciRepository extends JpaRepository<FibonacciNumber, Integer> {
}
