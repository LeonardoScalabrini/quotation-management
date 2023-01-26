package com.quotationmanagement.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quotationmanagement.domains.Quote;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class QuoteDTO {

  private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

  @NotBlank(message = "The buy date is required")
  public final String buyDate;

  @NotBlank(message = "The quatity is required")
  public final String quantity;

  @JsonCreator
  public QuoteDTO(
      @JsonProperty("buyDate") String buyDate, @JsonProperty("quantity") String quantity) {
    this.buyDate = buyDate;
    this.quantity = quantity;
  }

  public static QuoteDTO parseOf(Date buyDate, Integer quantity) {
    return new QuoteDTO(formatter.format(buyDate), quantity.toString());
  }

  public static QuoteDTO parseOf(Quote quote) {
    return new QuoteDTO(formatter.format(quote.date), quote.quantity.toString());
  }

  public Quote quoteValue() throws ParseException {
    return new Quote(formatter.parse(buyDate), Integer.valueOf(quantity));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QuoteDTO quoteDTO = (QuoteDTO) o;
    return buyDate.equals(quoteDTO.buyDate) && quantity.equals(quoteDTO.quantity);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buyDate, quantity);
  }
}
