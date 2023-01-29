package com.quotationmanagement.domains;

import java.util.Date;
import java.util.Objects;

public class Quote {
  private final Date date;
  private final Integer price;

  public Quote(Date date, Integer price) {
    Objects.requireNonNull(date);
    Objects.requireNonNull(price);
    this.date = date;
    this.price = price;
  }

  public Date getDate() {
    return date;
  }

  public Integer getPrice() {
    return price;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Quote quote = (Quote) o;
    return Objects.equals(date, quote.date) && Objects.equals(price, quote.price);
  }

  @Override
  public int hashCode() {
    return Objects.hash(date, price);
  }
}
