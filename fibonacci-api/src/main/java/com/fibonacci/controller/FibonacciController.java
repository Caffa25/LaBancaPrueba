package com.fibonacci.controller;

import com.fibonacci.business.FibonacciService;
import com.fibonacci.data.FibonacciStatsRepository;
import com.fibonacci.model.FibonacciStats;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fibonacci")
public class FibonacciController {

    private final FibonacciService service;
    private final FibonacciStatsRepository statsRepository;

    public FibonacciController(FibonacciService service, FibonacciStatsRepository statsRepository) {
        this.service = service;
        this.statsRepository = statsRepository;
    }

    @GetMapping("/{n}")
    public ResponseEntity<?> getFibonacci(@PathVariable int n) {
        if (n <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input: n must be a positive integer.");
        }

        long result = service.getFibonacci(n);
        return ResponseEntity.status(HttpStatus.OK).body("Fibonacci number: " + result);
    }

    @GetMapping("/stats")
    public ResponseEntity<List<FibonacciStats>> getStatistics() {
        List<FibonacciStats> stats = statsRepository.findAll();
        return ResponseEntity.ok(stats);
    }
}
