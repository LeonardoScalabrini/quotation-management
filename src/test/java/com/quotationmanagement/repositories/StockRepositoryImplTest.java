package com.quotationmanagement.repositories;

import static com.quotationmanagement.fixture.Fixture.QUOTE;
import static com.quotationmanagement.fixture.Fixture.STOCK;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import com.quotationmanagement.entities.QuoteEntity;
import com.quotationmanagement.entities.StockEntity;
import com.quotationmanagement.repositories.jpa.QuoteJpaRepository;
import com.quotationmanagement.repositories.jpa.StockJpaRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class StockRepositoryImplTest {
  @Mock private StockJpaRepository stockJpaRepository;
  @Mock private QuoteJpaRepository quoteJpaRepository;
  @InjectMocks private StockRepositoryImpl stockRepository;
  private StockEntity stockEntity = StockEntity.valueOf(STOCK);
  private QuoteEntity quoteEntity = QuoteEntity.valueOf(stockEntity.getId(), QUOTE);

  @BeforeEach
  void setUp() {
    when(stockJpaRepository.findByStockCod("id")).thenReturn(Optional.of(stockEntity));
    when(stockJpaRepository.findAll()).thenReturn(singletonList(stockEntity));
    when(stockJpaRepository.save(any())).thenReturn(stockEntity);
    when(quoteJpaRepository.findAllByStockId(stockEntity.getId()))
        .thenReturn(singletonList(quoteEntity));
    when(quoteJpaRepository.saveAll(any())).thenReturn(singletonList(quoteEntity));
  }

  @Test
  void findByStockCod() {
    var result = stockRepository.findByStockCod("id");
    assertTrue(result.isPresent());
    assertEquals(stockEntity.stockValue(singletonList(quoteEntity)), result.orElseThrow());
    verify(stockJpaRepository, times(1)).findByStockCod("id");
    verify(quoteJpaRepository, times(1)).findAllByStockId(stockEntity.getId());
  }

  @Test
  void stockNotExist() {
    when(stockJpaRepository.findByStockCod("id")).thenReturn(Optional.empty());
    var result = stockRepository.findByStockCod("id");
    assertTrue(result.isEmpty());
    verify(stockJpaRepository, times(1)).findByStockCod("id");
    verify(quoteJpaRepository, times(0)).findAllByStockId(any());
  }

  @Test
  void quoteEmpty() {
    when(quoteJpaRepository.findAllByStockId(stockEntity.getId())).thenReturn(emptyList());
    var result = stockRepository.findByStockCod("id");
    assertTrue(result.isPresent());
    assertEquals(stockEntity.stockValue(emptyList()), result.orElseThrow());
    verify(stockJpaRepository, times(1)).findByStockCod("id");
    verify(quoteJpaRepository, times(1)).findAllByStockId(any());
  }

  @Test
  void findAll() {
    var result = stockRepository.findAll();
    assertEquals(singletonList(stockEntity.stockValue(singletonList(quoteEntity))), result);
    verify(stockJpaRepository, times(1)).findAll();
    verify(quoteJpaRepository, times(1)).findAllByStockId(any());
  }

  @Test
  void findAllEmpty() {
    when(stockJpaRepository.findAll()).thenReturn(emptyList());
    var result = stockRepository.findAll();
    assertTrue(result.isEmpty());
    verify(stockJpaRepository, times(1)).findAll();
    verify(quoteJpaRepository, times(0)).findAllByStockId(any());
  }

  @Test
  void save() {
    var result = stockRepository.save(STOCK);
    assertEquals(STOCK, result);
    verify(stockJpaRepository, times(1)).save(any());
    verify(quoteJpaRepository, times(1)).saveAll(any());
  }
}
