package com.quotationmanagement.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quotationmanagement.domains.Quote;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class StockRequestDTO {
  @NotBlank public final String stockId;
  @Valid public final List<QuoteDTO> quotes;

  @JsonCreator
  public StockRequestDTO(
      @JsonProperty("stockId") String stockId, @JsonProperty("quotes") List<QuoteDTO> quotes) {
    this.stockId = stockId;
    this.quotes = quotes;
  }

  public List<Quote> quotesValue() throws ParseException {
    var result = new ArrayList<Quote>();
    for (var dto : quotes) result.add(dto.quoteValue());
    return result;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StockRequestDTO that = (StockRequestDTO) o;
    return Objects.equals(stockId, that.stockId) && Objects.equals(quotes, that.quotes);
  }

  @Override
  public int hashCode() {
    return Objects.hash(stockId, quotes);
  }
}
