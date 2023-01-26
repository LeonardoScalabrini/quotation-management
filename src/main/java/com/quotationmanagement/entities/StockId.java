package com.quotationmanagement.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

@Embeddable
public class StockId implements Serializable {

  public String value;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StockId stockId = (StockId) o;
    return Objects.equals(value, stockId.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
