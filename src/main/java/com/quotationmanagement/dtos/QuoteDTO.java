package com.quotationmanagement.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.quotationmanagement.domains.Quote;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.NotBlank;

public class QuoteDTO implements Serializable {

  @NotBlank(message = "The buy date is required")
  private String buyDate;

  @NotBlank(message = "The value is required")
  private String value;

  public String getBuyDate() {
    return buyDate;
  }

  public String getValue() {
    return value;
  }

  @JsonCreator
  public QuoteDTO(@JsonProperty("buyDate") String buyDate, @JsonProperty("value") String value) {
    this.buyDate = buyDate;
    this.value = value;
  }

  public static QuoteDTO parseOf(Date buyDate, Integer quantity) {
    return new QuoteDTO(new SimpleDateFormat("yyyy-MM-dd").format(buyDate), quantity.toString());
  }

  public static QuoteDTO parseOf(Quote quote) {
    return new QuoteDTO(
        new SimpleDateFormat("yyyy-MM-dd").format(quote.date), quote.price.toString());
  }

  public Quote quoteValue() throws ParseException {
    return new Quote(new SimpleDateFormat("yyyy-MM-dd").parse(buyDate), Integer.valueOf(value));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    QuoteDTO quoteDTO = (QuoteDTO) o;
    return Objects.equals(buyDate, quoteDTO.buyDate) && Objects.equals(value, quoteDTO.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(buyDate, value);
  }
}
