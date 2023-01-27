package com.quotationmanagement.controllers;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(StockCacheController.class)
class StockCacheControllerTest {
  @Autowired private MockMvc mockMvc;

  @Test
  void clean() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.delete("/stockcache").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
