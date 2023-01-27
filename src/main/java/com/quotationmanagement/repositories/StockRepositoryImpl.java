package com.quotationmanagement.repositories;

import com.quotationmanagement.domains.Stock;
import com.quotationmanagement.domains.interfaces.StockRepository;
import com.quotationmanagement.entities.QuoteEntity;
import com.quotationmanagement.entities.StockEntity;
import com.quotationmanagement.repositories.jpa.QuoteJpaRepository;
import com.quotationmanagement.repositories.jpa.StockJpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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
    var result = stockJpaRepository.findByStockCod(cod);
    if (result.isEmpty()) return Optional.empty();
    var stock = result.orElseThrow();
    var quotes = quoteJpaRepository.findAllByStockId(stock.getId());
    return Optional.of(stock.stockValue(quotes));
  }

  @Override
  public List<Stock> findAll() {
    var result = stockJpaRepository.findAll();
    return result.stream()
        .map(s -> s.stockValue(quoteJpaRepository.findAllByStockId(s.getId())))
        .collect(Collectors.toList());
  }

  @Override
  @Transactional
  public Stock save(Stock stock) {
    var stockEntity =
        stockJpaRepository
            .findByStockCod(stock.stockCod)
            .orElseGet(() -> stockJpaRepository.save(StockEntity.valueOf(stock)));
    quoteJpaRepository.saveAll(
        stock.quotes.stream()
            .map(q -> QuoteEntity.valueOf(stockEntity.getId(), q))
            .collect(Collectors.toList()));
    return stock;
  }
}
