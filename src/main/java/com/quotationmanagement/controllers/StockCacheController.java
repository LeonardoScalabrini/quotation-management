package com.quotationmanagement.controllers;

import com.quotationmanagement.externals.interfaces.StockManagerCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/stockcache")
public class StockCacheController {

  private final StockManagerCacheService stockManagerCacheService;

  @Autowired
  public StockCacheController(StockManagerCacheService stockManagerCacheService) {
    this.stockManagerCacheService = stockManagerCacheService;
  }

  @DeleteMapping
  public void clean() {
    stockManagerCacheService.clean();
  }
}
