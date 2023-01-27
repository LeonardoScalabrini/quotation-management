package com.quotationmanagement.controllers;

import com.quotationmanagement.externals.StockManagerServiceCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/stockcache")
public class StockCacheController {

  private final StockManagerServiceCache stockManagerServiceCache;

  @Autowired
  public StockCacheController(StockManagerServiceCache stockManagerServiceCache) {
    this.stockManagerServiceCache = stockManagerServiceCache;
  }

  @DeleteMapping
  public void clean() {
    stockManagerServiceCache.clean();
  }
}
