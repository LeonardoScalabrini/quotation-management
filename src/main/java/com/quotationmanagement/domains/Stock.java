package com.quotationmanagement.domains;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Stock {
  public final String id = UUID.randomUUID().toString();
  public final String stockId;
  public final List<Quote> quotes;

  public Stock(String stockId, List<Quote> quotes) {
    Objects.requireNonNull(stockId);
    Objects.requireNonNull(quotes);
    if (stockId.isBlank()) throw new IllegalArgumentException();
    this.stockId = stockId;
    this.quotes = quotes;
  }
}
