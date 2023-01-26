package com.quotationmanagement.entities;

import com.quotationmanagement.domains.Quote;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity(name = "quote")
public class QuoteEntity implements Serializable {
  @Id public String id = UUID.randomUUID().toString();
  @NotNull @Column public Date date;
  @NotNull @Column public Integer price;

  @NotNull
  @AttributeOverride(name = "value", column = @Column(name = "stockId"))
  public StockId stockId;

  public static QuoteEntity valueOf(StockId stockId, Quote quote) {
    var quoteEntity = new QuoteEntity();
    quoteEntity.stockId = stockId;
    quoteEntity.date = quote.date;
    quoteEntity.price = quote.price;
    return quoteEntity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QuoteEntity that = (QuoteEntity) o;
    return Objects.equals(id, that.id)
        && Objects.equals(date, that.date)
        && Objects.equals(price, that.price)
        && Objects.equals(stockId, that.stockId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, price, stockId);
  }

  public Quote quoteValue() {
    return new Quote(date, price);
  }
}
