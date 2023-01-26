package com.quotationmanagement.domains;

import java.util.Date;
import java.util.Objects;

public class Quote {
  public final Date date;
  public final Integer value;

  public Quote(Date date, Integer value) {
    Objects.requireNonNull(date);
    Objects.requireNonNull(value);
    this.date = date;
    this.value = value;
  }
}
