package com.fibonacci.business;

import com.fibonacci.data.FibonacciStatsRepository;
import com.fibonacci.model.FibonacciStats;
import org.springframework.stereotype.Service;

@Service
public class FibonacciStatsService {

    private final FibonacciStatsRepository statsRepository;
 

    public FibonacciStatsService(FibonacciStatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public void updateStats(int n) {
        statsRepository.findById((long)n).ifPresentOrElse(
            stats -> {
                stats.incrementCount();
                statsRepository.save(stats);
            },
            () -> statsRepository.save(new FibonacciStats(n, 1))
        );
    }
}
