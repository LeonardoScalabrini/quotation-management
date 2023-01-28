package com.quotationmanagement.entities;

import static com.quotationmanagement.fixture.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class QuoteEntityTest {

  @Test
  void equals() {
    var stockEntity = StockEntity.valueOf(STOCK);
    var quoteEntity = QuoteEntity.valueOf(stockEntity, QUOTE);
    var equals = QuoteEntity.valueOf(stockEntity, QUOTE);
    var notEquals = QuoteEntity.valueOf(stockEntity, QUOTE);
    assertNotNull(quoteEntity.getId());
    assertEquals(stockEntity, quoteEntity.getStock());
    assertEquals(QUOTE.price, quoteEntity.getPrice());
    assertEquals(QUOTE.date, quoteEntity.getDate());
    assertEquals(quoteEntity, quoteEntity);
    assertEquals(
        Objects.hash(quoteEntity.getId(), QUOTE.date, QUOTE.price, stockEntity),
        quoteEntity.hashCode());
    assertNotEquals(notEquals, quoteEntity);
    assertNotEquals(null, quoteEntity);
    assertNotEquals(new Object(), quoteEntity);
  }

  @Test
  void quoteValue() {
    var stockEntity = StockEntity.valueOf(STOCK);
    var quoteEntity = QuoteEntity.valueOf(stockEntity, QUOTE);
    assertEquals(QUOTE, quoteEntity.quoteValue());
  }
}
