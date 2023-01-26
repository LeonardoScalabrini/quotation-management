package com.quotationmanagement.usercases;

import com.quotationmanagement.domains.Quote;
import com.quotationmanagement.domains.Stock;
import com.quotationmanagement.domains.interfaces.CreateStockUserCase;
import com.quotationmanagement.domains.interfaces.StockRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreateStockUserCaseImpl implements CreateStockUserCase {

  private final StockRepository stockRepository;

  @Autowired
  public CreateStockUserCaseImpl(StockRepository stockRepository) {
    this.stockRepository = stockRepository;
  }

  @Override
  public Stock create(String stockId, List<Quote> quotes) {
    var stock = Stock.valueOf(stockId, quotes);
    stockRepository.save(stock);
    return stock;
  }
}
