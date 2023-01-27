package com.quotationmanagement.domains;

import static com.quotationmanagement.fixture.Fixture.*;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Objects;
import org.junit.jupiter.api.Test;

class StockTest {

  @Test
  public void test() {
    var stock = Stock.valueOf("id", "stockId", singletonList(QUOTE));
    var equals = Stock.valueOf("id", "stockId", singletonList(QUOTE));
    var notEquals = Stock.valueOf("notEquals", "notEquals", emptyList());
    assertEquals("id", stock.id);
    assertEquals("stockId", stock.stockCod);
    assertEquals(singletonList(QUOTE), stock.quotes);
    assertEquals(stock, stock);
    assertEquals(equals, stock);
    assertEquals(Objects.hash("id", "stockId", singletonList(QUOTE)), stock.hashCode());
    assertNotEquals(notEquals, stock);
    assertNotEquals(null, stock);
    assertNotEquals(new Object(), stock);
  }

  @Test
  public void notNull() {
    assertThrows(
        NullPointerException.class, () -> Stock.valueOf(null, "stockId", singletonList(QUOTE)));
    assertThrows(NullPointerException.class, () -> Stock.valueOf("id", null, singletonList(QUOTE)));
    assertThrows(NullPointerException.class, () -> Stock.valueOf("id", "stockId", null));
    assertThrows(NullPointerException.class, () -> Stock.valueOf(null, null, null));
  }

  @Test
  void valueOf() {
    var stock = Stock.valueOf("stockId", singletonList(QUOTE));
    assertNotNull(stock.id);
    assertEquals("stockId", stock.stockCod);
    assertEquals(singletonList(QUOTE), stock.quotes);
  }
}