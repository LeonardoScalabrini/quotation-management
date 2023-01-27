package com.quotationmanagement.domains.interfaces;

import com.quotationmanagement.domains.Quote;
import com.quotationmanagement.domains.Stock;
import com.quotationmanagement.domains.exceptions.StockNotRegistredException;
import java.util.List;

public interface CreateStockUserCase {
  Stock create(String stockId, List<Quote> quotes) throws StockNotRegistredException;
}
