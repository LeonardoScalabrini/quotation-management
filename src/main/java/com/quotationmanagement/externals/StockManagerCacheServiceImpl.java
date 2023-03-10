package com.quotationmanagement.externals;

import com.quotationmanagement.dtos.StockManagerDTO;
import com.quotationmanagement.dtos.StockManagerNotificationDTO;
import com.quotationmanagement.externals.interfaces.StockManagerCacheService;
import com.quotationmanagement.externals.interfaces.StockManagerService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

@Primary
@Service
@Scope("singleton")
public class StockManagerCacheServiceImpl implements StockManagerCacheService {

  private final StockManagerService stockManagerService;
  private List<StockManagerDTO> cache;

  @Autowired
  public StockManagerCacheServiceImpl(StockManagerService stockManagerService) {
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
