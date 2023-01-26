package com.quotationmanagement.domains;

import java.util.Date;
import java.util.Objects;

public class Quote {
  public final Date date;
  public final Integer price;

  public Quote(Date date, Integer price) {
    Objects.requireNonNull(date);
    Objects.requireNonNull(price);
    this.date = date;
    this.price = price;
  }
}
