package com.quotationmanagement.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quotationmanagement.domains.Stock;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StockResponseDTO {
  public final String id;
  public final String stockId;
  public final List<QuoteDTO> quotes;

  @JsonCreator
  public StockResponseDTO(
      @JsonProperty("id") String id,
      @JsonProperty("stockId") String stockId,
      @JsonProperty("quotes") List<QuoteDTO> quotes) {
    this.id = id;
    this.stockId = stockId;
    this.quotes = quotes;
  }

  public static StockResponseDTO parseOf(Stock stock) {
    return new StockResponseDTO(
        stock.id,
        stock.stockCod,
        stock.quotes.stream().map(QuoteDTO::parseOf).collect(Collectors.toList()));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StockResponseDTO that = (StockResponseDTO) o;
    return Objects.equals(id, that.id)
        && Objects.equals(stockId, that.stockId)
        && Objects.equals(quotes, that.quotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, stockId, quotes);
  }
}
