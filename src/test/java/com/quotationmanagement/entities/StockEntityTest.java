package com.quotationmanagement.entities;

import static com.quotationmanagement.fixture.Fixture.STOCK;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;

import com.quotationmanagement.domains.Stock;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class StockEntityTest {

  @Test
  void equals() {
    var stockEntity = StockEntity.valueOf(STOCK);
    var equals = StockEntity.valueOf(STOCK);
    var notEquals = StockEntity.valueOf(Stock.valueOf("id2", emptyList()));
    assertEquals(STOCK.getId(), stockEntity.getId());
    assertEquals(STOCK.getStockCod(), stockEntity.getStockCod());
    assertEquals(stockEntity, stockEntity);
    assertEquals(Objects.hash(STOCK.getId(), STOCK.getStockCod()), stockEntity.hashCode());
    assertEquals(equals, stockEntity);
    assertNotEquals(notEquals, stockEntity);
    assertNotEquals(null, stockEntity);
    assertNotEquals(new Object(), stockEntity);
  }

  @Test
  void stockValue() {
    var stockEntity = StockEntity.valueOf(STOCK);
    var stockValue = stockEntity.stockValue();
    assertEquals(STOCK.getId(), stockValue.getId());
    assertEquals(STOCK.getStockCod(), stockValue.getStockCod());
  }
}
