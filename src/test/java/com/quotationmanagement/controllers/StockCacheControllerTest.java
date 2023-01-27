package com.quotationmanagement.controllers;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.quotationmanagement.externals.StockManagerServiceCache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@WebMvcTest(StockCacheController.class)
class StockCacheControllerTest {

  @MockBean private StockManagerServiceCache stockManagerServiceCache;
  @Autowired private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    doNothing().when(stockManagerServiceCache).clean();
  }

  @Test
  void clean() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.delete("/stockcache").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    verify(stockManagerServiceCache, times(1)).clean();
  }
}
