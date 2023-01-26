package com.quotationmanagement.usercases;

import com.quotationmanagement.domains.Stock;
import com.quotationmanagement.domains.interfaces.FindStockUserCase;
import com.quotationmanagement.domains.interfaces.StockRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FindStockUserCaseImpl implements FindStockUserCase {
  private final StockRepository stockRepository;

  @Autowired
  public FindStockUserCaseImpl(StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  @Override
  public Optional<Stock> findByStockId(String stockId) {
    return stockRepository.findByStockId(stockId);
  }

  @Override
  public List<Stock> findAll() {
    return stockRepository.findAll();
  }
}
