package com.fibonacci.controller;

import com.fibonacci.business.FibonacciService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(FibonacciController.class)
public class FibonacciControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private FibonacciService service;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    void shouldReturnFibonacciNumber() throws Exception {
        int input = 6;
        long expectedResult = 8; // Fibonacci(6) = 8

        Mockito.when(service.getFibonacci(input)).thenReturn(expectedResult);

        mockMvc.perform(get("/fibonacci/" + input)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(" Fibonacci number: 8"));
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
}
