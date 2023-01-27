package com.quotationmanagement.externalServices;

import com.quotationmanagement.dtos.StockManagerDTO;
import com.quotationmanagement.dtos.StockManagerNotificationDTO;
import com.quotationmanagement.externalServices.interfaces.StockManagerService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Primary
@Service
public class StockManagerServiceCache implements StockManagerService {

  private final StockManagerServiceImpl stockManagerService;
  private static List<StockManagerDTO> cache;

  @Autowired
  public StockManagerServiceCache(StockManagerServiceImpl stockManagerService) {
    this.stockManagerService = stockManagerService;
  }

  @Override
  public List<StockManagerDTO> getStocks() throws HttpStatusCodeException {
    if (Objects.isNull(cache)) cache = stockManagerService.getStocks();
    return cache;
  }

  @Override
  public List<StockManagerNotificationDTO> notification(StockManagerNotificationDTO request) {
    return stockManagerService.notification(request);
  }

  public void clean() {
    cache = null;
  }
}
