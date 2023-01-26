package com.quotationmanagement.domains;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Stock {
  public final String id;
  public final String stockId;
  public final List<Quote> quotes;

  private Stock(String id, String stockId, List<Quote> quotes) {
    Objects.requireNonNull(id);
    Objects.requireNonNull(stockId);
    Objects.requireNonNull(quotes);
    if (id.isBlank()) throw new IllegalArgumentException();
    if (stockId.isBlank()) throw new IllegalArgumentException();
    this.id = id;
    this.stockId = stockId;
    this.quotes = quotes;
  }

  public static Stock valueOf(String stockId, List<Quote> quotes) {
    return new Stock(UUID.randomUUID().toString(), stockId, quotes);
  }

  public static Stock valueOf(String id, String stockId, List<Quote> quotes) {
    return new Stock(id, stockId, quotes);
  }
}
