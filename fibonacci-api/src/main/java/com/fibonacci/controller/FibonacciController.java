package com.fibonacci.controller;

import com.fibonacci.business.FibonacciService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fibonacci")
public class FibonacciController {

    private final FibonacciService service;

    public FibonacciController(FibonacciService service) {
        this.service = service;
    }

    @GetMapping("/{n}")
    public ResponseEntity<?> getFibonacci(@PathVariable int n) {
        if (n <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Invalid input: n must be a positive integer.");
        }

        long result = service.getFibonacci(n);
        return ResponseEntity.ok(HttpStatus.OK + " Fibonacci number: " + result);
    }
}
