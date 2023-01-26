package com.quotationmanagement.entities;

import com.quotationmanagement.domains.Stock;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class StockEntity implements Serializable {
  @Id public String id;

  @NotBlank
  @Column(unique = true)
  public String stockId;

  @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL)
  public List<QuoteEntity> quotes;

  public Stock stockValue() {
    return Stock.valueOf(
        id, stockId, quotes.stream().map(QuoteEntity::quoteValue).collect(Collectors.toList()));
  }

  public static StockEntity valueOf(Stock stock) {
    var stockEntity = new StockEntity();
    stockEntity.id = stock.id;
    stockEntity.stockId = stock.stockId;
    stockEntity.quotes =
        stock.quotes.stream().map(QuoteEntity::valueOf).collect(Collectors.toList());
    return stockEntity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StockEntity that = (StockEntity) o;
    return Objects.equals(id, that.id)
        && Objects.equals(stockId, that.stockId)
        && Objects.equals(quotes, that.quotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, stockId, quotes);
  }
}
