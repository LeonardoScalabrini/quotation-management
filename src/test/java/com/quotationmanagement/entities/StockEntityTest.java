package com.quotationmanagement.entities;

import static com.quotationmanagement.fixture.Fixture.STOCK;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

import com.quotationmanagement.domains.Stock;
import java.util.Objects;
import java.util.stream.Collectors;
import org.junit.jupiter.api.Test;

class StockEntityTest {

  @Test
  void equals() {
    var stockEntity = StockEntity.valueOf(STOCK);
    var equals = StockEntity.valueOf(STOCK);
    var notEquals = StockEntity.valueOf(Stock.valueOf("id2", emptyList()));
    assertEquals(STOCK.id, stockEntity.getId().getValue());
    assertEquals(STOCK.stockCod, stockEntity.getStockCod());
    assertEquals(stockEntity, stockEntity);
    assertEquals(Objects.hash(StockId.valueOf(STOCK.id), STOCK.stockCod), stockEntity.hashCode());
    assertEquals(equals, stockEntity);
    assertNotEquals(notEquals, stockEntity);
    assertNotEquals(null, stockEntity);
    assertNotEquals(new Object(), stockEntity);
  }

  @Test
  void stockValue() {
    var stockEntity = StockEntity.valueOf(STOCK);
    assertEquals(
        STOCK,
        stockEntity.stockValue(
            STOCK.quotes.stream()
                .map(q -> QuoteEntity.valueOf(stockEntity.getId(), q))
                .collect(Collectors.toList())));
  }
}
