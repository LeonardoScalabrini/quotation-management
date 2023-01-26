package com.quotationmanagement.domains;

import java.util.Date;
import java.util.Objects;

public class Quote {
  public final Date date;
  public final Integer quantity;

  public Quote(Date date, Integer quantity) {
    Objects.requireNonNull(date);
    Objects.requireNonNull(quantity);
    this.date = date;
    this.quantity = quantity;
  }
}
