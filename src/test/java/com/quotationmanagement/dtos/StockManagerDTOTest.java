package com.quotationmanagement.dtos;

import static com.quotationmanagement.fixture.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class StockManagerDTOTest {
  @Test
  void parseOf() {
    assertEquals("petr7", STOCK_MANAGER_DTO.getId());
    assertEquals("test petr", STOCK_MANAGER_DTO.getDescription());
  }

  @Test
  void fromJson() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    var result = mapper.readValue(new File(STOCK_MANAGER_STOCK_PATH), StockManagerDTO.class);
    assertEquals(STOCK_MANAGER_DTO, result);
  }
}
