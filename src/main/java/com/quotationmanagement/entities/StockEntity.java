package com.quotationmanagement.entities;

import static java.util.stream.Collectors.*;

import com.quotationmanagement.domains.Stock;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import org.hibernate.annotations.Immutable;

@Immutable
@Entity(name = "stock")
public class StockEntity implements Serializable {
  @Id private String id;

  @Column(unique = true)
  @NotBlank
  private String stockCod;

  @OneToMany(mappedBy = "stock", orphanRemoval = true)
  private List<QuoteEntity> quotes = new ArrayList<>();

  private StockEntity() {}

  private StockEntity(String id, String stockCod) {
    this.id = id;
    this.stockCod = stockCod;
  }

  public String getId() {
    return id;
  }

  public String getStockCod() {
    return stockCod;
  }

  public List<QuoteEntity> getQuotes() {
    return quotes;
  }

  public Stock stockValue() {
    return Stock.valueOf(
        id, stockCod, quotes.stream().map(QuoteEntity::quoteValue).collect(toList()));
  }

  public static StockEntity valueOf(Stock stock) {
    return new StockEntity(stock.getId(), stock.getStockCod());
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
