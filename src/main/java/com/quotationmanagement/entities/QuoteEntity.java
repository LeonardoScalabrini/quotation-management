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
  @Id private String id = UUID.randomUUID().toString();
  @NotNull @Column private Date date;
  @NotNull @Column private Integer price;

  @NotNull
  @AttributeOverride(name = "value", column = @Column(name = "stockId"))
  private StockId stockId;

  private QuoteEntity() {}

  private QuoteEntity(Date date, Integer price, StockId stockId) {
    this.date = date;
    this.price = price;
    this.stockId = stockId;
  }

  public String getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public Integer getPrice() {
    return price;
  }

  public StockId getStockId() {
    return stockId;
  }

  public static QuoteEntity valueOf(StockId stockId, Quote quote) {
    return new QuoteEntity(quote.date, quote.price, stockId);
  }

  public Quote quoteValue() {
    return new Quote(date, price);
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
}
