package com.quotationmanagement.configurations;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ApplicationConfigImpl.class)
class ApplicationConfigTest {

  @Autowired private ApplicationConfigImpl applicationConfig;

  @Test
  void getPort() {
    assertEquals("localhost", applicationConfig.getHost());
  }

  @Test
  void getHost() {
    assertEquals(8081, applicationConfig.getPort());
  }
}
