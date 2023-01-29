package com.quotationmanagement.configurations;

import com.quotationmanagement.configurations.interfaces.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfigImpl implements ApplicationConfig {
  private final String host;
  private final Integer port;

  @Autowired
  public ApplicationConfigImpl(
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
