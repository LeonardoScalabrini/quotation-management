package com.quotationmanagement.externals;

import static java.util.Collections.emptyList;

import com.quotationmanagement.configurations.interfaces.StockManagerConfig;
import com.quotationmanagement.dtos.StockManagerDTO;
import com.quotationmanagement.dtos.StockManagerNotificationDTO;
import com.quotationmanagement.externals.interfaces.StockManagerService;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@Service
public class StockManagerServiceImpl implements StockManagerService {

  private final RestTemplate restTemplate;
  private final StockManagerConfig stockManagerConfig;

  @Autowired
  public StockManagerServiceImpl(
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
    return emptyList();
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
    return emptyList();
  }
}
