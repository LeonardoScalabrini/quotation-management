package com.quotationmanagement.externals;

import com.quotationmanagement.domains.interfaces.FindRegistredStock;
import com.quotationmanagement.externals.interfaces.StockManagerService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindByStockManager implements FindRegistredStock {

  private final StockManagerService stockManagerService;

  @Autowired
  public FindByStockManager(StockManagerService stockManagerService) {
    this.stockManagerService = stockManagerService;
  }

  @Override
  public Optional<String> find(String stockId) {
    return stockManagerService.getStocks().stream()
        .filter(stockManagerDTO -> stockManagerDTO.id.equalsIgnoreCase(stockId))
        .map(stockManagerDTO -> stockManagerDTO.id)
        .findAny();
  }
}
