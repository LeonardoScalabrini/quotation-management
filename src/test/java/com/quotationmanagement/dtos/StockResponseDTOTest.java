package com.quotationmanagement.dtos;

import static com.quotationmanagement.fixture.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class StockResponseDTOTest {

  @Test
  void fromJson() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    var result = mapper.readValue(new File(STOCK_RESPONSE_PATH), StockResponseDTO.class);
    assertEquals(STOCK_RESPONSE_DTO, result);
  }
}
