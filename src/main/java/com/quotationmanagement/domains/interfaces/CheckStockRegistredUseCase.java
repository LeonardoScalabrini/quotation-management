package com.quotationmanagement.domains.interfaces;

import com.quotationmanagement.domains.exceptions.StockNotRegistredException;

public interface CheckStockRegistredUseCase {
  void check(String stockId) throws StockNotRegistredException;
}
