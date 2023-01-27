package com.quotationmanagement.entities;

import static com.quotationmanagement.fixture.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class QuoteEntityTest {

  @Test
  void equals() {
    var stockId = StockId.valueOf("id");
    var quoteEntity = QuoteEntity.valueOf(stockId, QUOTE);
    var equals = QuoteEntity.valueOf(stockId, QUOTE);
    var notEquals = QuoteEntity.valueOf(StockId.valueOf("id2"), QUOTE);
    assertNotNull(quoteEntity.getId());
    assertEquals(stockId, quoteEntity.getStockId());
    assertEquals(QUOTE.price, quoteEntity.getPrice());
    assertEquals(QUOTE.date, quoteEntity.getDate());
    assertEquals(quoteEntity, quoteEntity);
    assertEquals(
        Objects.hash(quoteEntity.getId(), QUOTE.date, QUOTE.price, stockId),
        quoteEntity.hashCode());
    assertNotEquals(notEquals, quoteEntity);
    assertNotEquals(null, quoteEntity);
    assertNotEquals(new Object(), quoteEntity);
  }

  @Test
  void quoteValue() {
    var stockId = StockId.valueOf("id");
    var quoteEntity = QuoteEntity.valueOf(stockId, QUOTE);
    assertEquals(QUOTE, quoteEntity.quoteValue());
  }
}
