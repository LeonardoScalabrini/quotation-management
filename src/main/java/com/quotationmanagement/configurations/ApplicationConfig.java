package com.quotationmanagement.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {
  private String host;
  private Integer port;

  @Autowired
  public ApplicationConfig(
      @Value("${server.host}") String host, @Value("${server.port}") Integer port) {
    this.host = host;
    this.port = port;
  }

  public Integer getPort() {
    return port;
  }

  public String getHost() {
    return host;
  }
}
