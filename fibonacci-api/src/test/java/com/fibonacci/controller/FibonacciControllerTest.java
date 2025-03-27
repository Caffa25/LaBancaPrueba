package com.fibonacci.controller;

import com.fibonacci.business.FibonacciService;
import com.fibonacci.data.FibonacciStatsRepository;
import com.fibonacci.model.FibonacciStats;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FibonacciController.class)
public class FibonacciControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FibonacciService service;

    @MockBean
    private FibonacciStatsRepository statsRepository; 

    @BeforeEach
    void setUp() {
    }

    @Test
    void shouldReturnFibonacciNumber() throws Exception {
        int input = 6;
        long expectedResult = 8; 

        Mockito.when(service.getFibonacci(input)).thenReturn(expectedResult);

        mockMvc.perform(get("/fibonacci/" + input)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Fibonacci number: 8")); 
    }

    @Test
    void shouldReturnBadRequestForNegativeInput() throws Exception {
        int input = -3;

        mockMvc.perform(get("/fibonacci/" + input)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid input: n must be a positive integer."));
    }

    @Test
    void shouldReturnBadRequestForZeroInput() throws Exception {
        int input = 0;

        mockMvc.perform(get("/fibonacci/" + input)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid input: n must be a positive integer."));
    }

    @Test
    void shouldReturnFibonacciStats() throws Exception {
        List<FibonacciStats> mockStats = Arrays.asList(
                new FibonacciStats(5, 3),
                new FibonacciStats(8, 7)
        );

        Mockito.when(statsRepository.findAll()).thenReturn(mockStats);

        mockMvc.perform(get("/fibonacci/stats")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("[{\"n\":5,\"count\":3},{\"n\":8,\"count\":7}]"));
    }
}
