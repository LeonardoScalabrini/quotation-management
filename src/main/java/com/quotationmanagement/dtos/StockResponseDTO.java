package com.quotationmanagement.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quotationmanagement.domains.Stock;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class StockResponseDTO implements Serializable {
  private final String id;
  private final String stockId;
  private final List<QuoteDTO> quotes;

  @JsonCreator
  public StockResponseDTO(
      @JsonProperty("id") String id,
      @JsonProperty("stockId") String stockId,
      @JsonProperty("quotes") List<QuoteDTO> quotes) {
    this.id = id;
    this.stockId = stockId;
    this.quotes = quotes;
  }

  public String getId() {
    return id;
  }

  public String getStockId() {
    return stockId;
  }

  public List<QuoteDTO> getQuotes() {
    return quotes;
  }

  public static StockResponseDTO parseOf(Stock stock) {
    return new StockResponseDTO(
        stock.getId(),
        stock.getStockCod(),
        stock.getQuotes().stream().map(QuoteDTO::parseOf).collect(Collectors.toList()));
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
