package com.quotationmanagement.usercases;

import com.quotationmanagement.domains.Stock;
import com.quotationmanagement.domains.interfaces.FindStockUserCase;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public class FindStockUserCaseImpl implements FindStockUserCase {
  @Override
  public Optional<Stock> findByStockId(String stockId) {
    return Optional.empty();
  }

  @Override
  public List<Stock> findAll() {
    return Collections.emptyList();
  }
}
