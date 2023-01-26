package com.quotationmanagement.dtos;

import static com.quotationmanagement.fixture.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class StockRequestDTOTest {

  @Test
  public void fromJson() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    var result = mapper.readValue(new File(STOCK_REQUEST_PATH), StockRequestDTO.class);
    assertEquals(STOCK_REQUEST_DTO, result);
  }
}
