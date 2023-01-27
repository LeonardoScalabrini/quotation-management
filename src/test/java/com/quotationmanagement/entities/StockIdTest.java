package com.quotationmanagement.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class StockIdTest {

  @Test
  void equals() {
    var stockId = StockId.valueOf("id");
    var equals = StockId.valueOf("id");
    var notEquals = StockId.valueOf("id2");
    assertEquals("id", stockId.getValue());
    assertEquals(stockId, stockId);
    assertEquals(Objects.hash("id"), stockId.hashCode());
    assertEquals(equals, stockId);
    assertNotEquals(notEquals, stockId);
    assertNotEquals(null, stockId);
    assertNotEquals(new Object(), stockId);
  }
}
