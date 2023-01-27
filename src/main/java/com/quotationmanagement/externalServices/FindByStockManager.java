package com.quotationmanagement.externalServices;

import com.quotationmanagement.domains.interfaces.FindRegistredStock;
import com.quotationmanagement.externalServices.interfaces.StockManagerService;
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
    var result = stockManagerService.getStocks();
    var match =
        result.stream()
            .filter(stockManagerDTO -> stockManagerDTO.id.equalsIgnoreCase(stockId))
            .findFirst();
    return match.map(stockManagerDTO -> stockId);
  }
}
