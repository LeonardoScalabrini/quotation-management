package com.quotationmanagement.repositories;

import com.quotationmanagement.domains.Stock;
import com.quotationmanagement.domains.interfaces.StockRepository;
import com.quotationmanagement.entities.StockEntity;
import com.quotationmanagement.repositories.jpa.StockJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockRepositoryImpl implements StockRepository {
  private final StockJpaRepository stockJpaRepository;

  @Autowired
  public StockRepositoryImpl(StockJpaRepository stockJpaRepository) {
    this.stockJpaRepository = stockJpaRepository;
  }

  @Override
  public Optional<Stock> findByStockId(String id) {
    var result = stockJpaRepository.findByStockId(id);
    if (result.isEmpty()) return Optional.empty();
    return Optional.of(result.orElseThrow().stockValue());
  }

  @Override
  public List<Stock> findAll() {
    var result = stockJpaRepository.findAll();
    return result.stream().map(StockEntity::stockValue).collect(Collectors.toList());
  }

  @Override
  public Stock save(Stock stock) {
    stockJpaRepository.save(StockEntity.valueOf(stock));
    return stock;
  }
}
