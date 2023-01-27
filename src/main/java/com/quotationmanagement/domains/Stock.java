package com.quotationmanagement.domains;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Stock {
  public final String id;
  public final String stockCod;
  public final List<Quote> quotes;

  private Stock(String id, String stockCod, List<Quote> quotes) {
    Objects.requireNonNull(id);
    Objects.requireNonNull(stockCod);
    Objects.requireNonNull(quotes);
    if (id.isBlank()) throw new IllegalArgumentException();
    if (stockCod.isBlank()) throw new IllegalArgumentException();
    this.id = id;
    this.stockCod = stockCod;
    this.quotes = quotes;
  }

  public static Stock valueOf(String stockId, List<Quote> quotes) {
    return new Stock(UUID.randomUUID().toString(), stockId, quotes);
  }

  public static Stock valueOf(String id, String stockId, List<Quote> quotes) {
    return new Stock(id, stockId, quotes);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Stock stock = (Stock) o;
    return Objects.equals(id, stock.id)
        && Objects.equals(stockCod, stock.stockCod)
        && Objects.equals(quotes, stock.quotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, stockCod, quotes);
  }
}
