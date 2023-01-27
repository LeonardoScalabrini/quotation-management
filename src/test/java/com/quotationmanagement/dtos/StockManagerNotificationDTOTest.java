package com.quotationmanagement.dtos;

import static com.quotationmanagement.fixture.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class StockManagerNotificationDTOTest {
  @Test
  void parseOf() {
    assertEquals("localhost", STOCK_MANAGER_NOTIFICATION_DTO.host);
    assertEquals(8081, STOCK_MANAGER_NOTIFICATION_DTO.port);
  }

  @Test
  void fromJson() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    var result =
        mapper.readValue(
            new File(STOCK_MANAGER_NOTIFICATION_PATH), StockManagerNotificationDTO.class);
    assertEquals(STOCK_MANAGER_NOTIFICATION_DTO, result);
  }
}
