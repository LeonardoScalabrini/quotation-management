package com.quotationmanagement.domains.interfaces;

import com.quotationmanagement.domains.Stock;
import java.util.List;
import java.util.Optional;

public interface FindStockUserCase {
  Optional<Stock> findByStockCod(String stockCod);

  List<Stock> findAll();
}
