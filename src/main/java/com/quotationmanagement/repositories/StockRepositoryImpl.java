package com.quotationmanagement.repositories;

import static java.util.stream.Collectors.toList;

import com.quotationmanagement.domains.Stock;
import com.quotationmanagement.domains.interfaces.StockRepository;
import com.quotationmanagement.entities.QuoteEntity;
import com.quotationmanagement.entities.StockEntity;
import com.quotationmanagement.repositories.jpa.QuoteJpaRepository;
import com.quotationmanagement.repositories.jpa.StockJpaRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StockRepositoryImpl implements StockRepository {
  private final StockJpaRepository stockJpaRepository;
  private final QuoteJpaRepository quoteJpaRepository;

  @Autowired
  public StockRepositoryImpl(
      StockJpaRepository stockJpaRepository, QuoteJpaRepository quoteJpaRepository) {
    this.stockJpaRepository = stockJpaRepository;
    this.quoteJpaRepository = quoteJpaRepository;
  }

  @Override
  public Optional<Stock> findByStockCod(String cod) {
    return stockJpaRepository.findByStockCod(cod).map(StockEntity::stockValue);
  }

  @Override
  public List<Stock> findAll() {
    var result = stockJpaRepository.findAll();
    return result.stream().map(StockEntity::stockValue).collect(toList());
  }

  @Override
  @Transactional
  public Stock save(Stock stock) {
    var stockEntity =
        stockJpaRepository
            .findByStockCod(stock.getStockCod())
            .orElseGet(() -> stockJpaRepository.save(StockEntity.valueOf(stock)));
    quoteJpaRepository.saveAll(
        stock.getQuotes().stream().map(q -> QuoteEntity.valueOf(stockEntity, q)).collect(toList()));
    return stock;
  }
}
