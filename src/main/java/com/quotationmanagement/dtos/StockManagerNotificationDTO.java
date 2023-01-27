package com.quotationmanagement.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Objects;

public class StockManagerNotificationDTO implements Serializable {
  public final String host;
  public final Integer port;

  @JsonCreator
  public StockManagerNotificationDTO(
      @JsonProperty("host") String host, @JsonProperty("port") Integer port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    StockManagerNotificationDTO that = (StockManagerNotificationDTO) o;
    return Objects.equals(host, that.host) && Objects.equals(port, that.port);
  }

  @Override
  public int hashCode() {
    return Objects.hash(host, port);
  }
}
