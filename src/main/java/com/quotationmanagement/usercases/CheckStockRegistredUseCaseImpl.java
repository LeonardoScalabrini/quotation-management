package com.quotationmanagement.usercases;

import com.quotationmanagement.domains.exceptions.StockNotRegistredException;
import com.quotationmanagement.domains.interfaces.CheckStockRegistredUseCase;
import com.quotationmanagement.domains.interfaces.FindRegistredStock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CheckStockRegistredUseCaseImpl implements CheckStockRegistredUseCase {

  private final FindRegistredStock findRegistredStock;

  @Autowired
  public CheckStockRegistredUseCaseImpl(FindRegistredStock findRegistredStock) {
    this.findRegistredStock = findRegistredStock;
  }

  @Override
  public void check(String stockId) throws StockNotRegistredException {
    var result = findRegistredStock.find(stockId);
    if (result.isPresent()) return;
    throw new StockNotRegistredException();
  }
}
