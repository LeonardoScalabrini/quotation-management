package com.quotationmanagement.configurations;

import com.quotationmanagement.configurations.interfaces.StockManagerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StockManagerConfigImpl implements StockManagerConfig {
  private final String url;

  @Autowired
  public StockManagerConfigImpl(@Value("${stock.manager.url}") String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }
}
