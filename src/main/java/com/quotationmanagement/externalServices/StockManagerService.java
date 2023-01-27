package com.quotationmanagement.externalServices;

import com.quotationmanagement.configurations.StockManagerConfig;
import com.quotationmanagement.dtos.StockManagerDTO;
import com.quotationmanagement.dtos.StockManagerNotificationDTO;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class StockManagerService {

  private final RestTemplate restTemplate;
  private final StockManagerConfig stockManagerConfig;

  @Autowired
  public StockManagerService(
      RestTemplateBuilder restTemplateBuilder, StockManagerConfig stockManagerConfig) {
    this.restTemplate = restTemplateBuilder.build();
    this.stockManagerConfig = stockManagerConfig;
  }

  public List<StockManagerDTO> getStocks() throws HttpStatusCodeException {
    var result =
        restTemplate.getForEntity(
            String.format("%s/stock", stockManagerConfig.getUrl()), StockManagerDTO[].class);
    if (result.getStatusCode().is2xxSuccessful() && result.hasBody())
      return Arrays.asList(Objects.requireNonNull(result.getBody()));
    return Collections.emptyList();
  }

  public List<StockManagerNotificationDTO> notification(StockManagerNotificationDTO request)
      throws HttpStatusCodeException {
    var result =
        restTemplate.postForEntity(
            String.format("%s/notification", stockManagerConfig.getUrl()),
            request,
            StockManagerNotificationDTO[].class);
    if (result.getStatusCode().is2xxSuccessful() && result.hasBody())
      return Arrays.asList(Objects.requireNonNull(result.getBody()));
    return Collections.emptyList();
  }
}
