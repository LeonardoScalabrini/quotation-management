package com.quotationmanagement.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;

public class StockManagerDTO implements Serializable {
  private final String id;
  private final String description;

  @JsonCreator
  public StockManagerDTO(
      @JsonProperty("id") String id, @JsonProperty("description") String description) {
    this.id = id;
    this.description = description;
  }

  public String getId() {
    return id;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StockManagerDTO that = (StockManagerDTO) o;
    return Objects.equals(id, that.id) && Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description);
  }
}
