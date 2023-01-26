package com.quotationmanagement.usercases;

import com.quotationmanagement.domains.Quote;
import com.quotationmanagement.domains.Stock;
import com.quotationmanagement.domains.interfaces.CreateStockUserCase;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CreateStockUserCaseImpl implements CreateStockUserCase {
  @Override
  public Stock create(String stockId, List<Quote> quotes) {
    return new Stock(stockId, quotes);
  }
}
