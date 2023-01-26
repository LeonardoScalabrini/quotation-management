package com.quotationmanagement.entities;

import com.quotationmanagement.domains.Stock;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity(name = "stock")
public class StockEntity implements Serializable {
  @AttributeOverride(name = "value", column = @Column(name = "id"))
  @EmbeddedId()
  public StockId id;

  @NotBlank
  @Column(unique = true)
  public String stockCod;

  public Stock stockValue(List<QuoteEntity> quotes) {
    return Stock.valueOf(
        id.value,
        stockCod,
        quotes.stream().map(QuoteEntity::quoteValue).collect(Collectors.toList()));
  }

  public static StockEntity valueOf(Stock stock) {
    var stockEntity = new StockEntity();
    stockEntity.id = new StockId();
    stockEntity.id.value = stock.id;
    stockEntity.stockCod = stock.stockCod;
    return stockEntity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StockEntity that = (StockEntity) o;
    return Objects.equals(id, that.id) && Objects.equals(stockCod, that.stockCod);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, stockCod);
  }
}
