package com.quotationmanagement.entities;

import com.quotationmanagement.domains.Quote;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class QuoteEntity implements Serializable {
  @Id @GeneratedValue public String id;
  public Date date;
  public Integer value;

  @ManyToOne public StockEntity stock;

  public static QuoteEntity valueOf(Quote quote) {
    var quoteEntity = new QuoteEntity();
    quoteEntity.date = quote.date;
    quoteEntity.value = quote.value;
    return quoteEntity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QuoteEntity that = (QuoteEntity) o;
    return Objects.equals(id, that.id)
        && Objects.equals(date, that.date)
        && Objects.equals(value, that.value)
        && Objects.equals(stock, that.stock);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, date, value, stock);
  }

  public Quote quoteValue() {
    return new Quote(date, value);
  }
}
