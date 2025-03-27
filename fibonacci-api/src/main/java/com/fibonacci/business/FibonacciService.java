package com.fibonacci.business;

import com.fibonacci.data.FibonacciRepository;
import com.fibonacci.model.FibonacciNumber;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class FibonacciService {

    private final FibonacciRepository repository;

    public FibonacciService(FibonacciRepository repository) {
        this.repository = repository;
    }

    public long getFibonacci(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must be a positive integer");
        if (n == 1) return 0;
        if (n == 2) return 1;
    
        Optional<FibonacciNumber> cached = repository.findById(n);
        if (cached.isPresent()) {
            return cached.get().getValue();
        }
    
        long a = 0, b = 1;
        for (int i = 3; i <= n; i++) {
            long temp = a + b;
            a = b;
            b = temp;
        }
    
        repository.save(new FibonacciNumber(n, b));  // Guarda "b"
        return b;  // Devuelve "b"
    }
    
    
}
