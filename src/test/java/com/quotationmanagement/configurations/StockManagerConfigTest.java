package com.quotationmanagement.configurations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = StockManagerConfigImpl.class)
class StockManagerConfigTest {

  @Autowired private StockManagerConfigImpl stockManagerConfig;

  @Test
  void url() {
    assertEquals("http://localhost:8080", stockManagerConfig.getUrl());
  }
}
