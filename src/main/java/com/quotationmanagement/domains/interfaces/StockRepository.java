package com.quotationmanagement.domains.interfaces;

import com.quotationmanagement.domains.Stock;
import java.util.List;
import java.util.Optional;

public interface StockRepository {

  Optional<Stock> findByStockCod(String stockCod);

  List<Stock> findAll();

  Stock save(Stock stock);
}
