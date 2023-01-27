package com.quotationmanagement.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockManagerConfig {
  public final String url;

  @Autowired
  public StockManagerConfig(@Value("${stock.manager.url}") String url) {
    this.url = url;
  }
}
