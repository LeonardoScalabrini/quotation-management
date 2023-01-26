package com.quotationmanagement.dtos;

import static com.quotationmanagement.fixture.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;

class QuoteDTOTest {

  @Test
  public void parseOf() {
    assertEquals("2019-01-01", QUOTE_DTO.getBuyDate());
    assertEquals("10", QUOTE_DTO.getValue());
  }

  @Test
  public void fromJson() throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    var result = mapper.readValue(new File(QUOTE_PATH), QuoteDTO.class);
    assertEquals(QUOTE_DTO, result);
  }
}
