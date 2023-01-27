package com.quotationmanagement.usercases;

import static com.quotationmanagement.fixture.Fixture.STOCK;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.quotationmanagement.domains.interfaces.StockRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class FindStockUserCaseImplTest {

  @Mock private StockRepository stockRepository;
  @InjectMocks private FindStockUserCaseImpl findStockUserCase;

  @BeforeEach
  void setUp() {
    when(stockRepository.findAll()).thenReturn(singletonList(STOCK));
    when(stockRepository.findByStockCod("id")).thenReturn(Optional.of(STOCK));
  }

  @Test
  void findByStockCod() {
    var result = findStockUserCase.findByStockCod("id");
    assertTrue(result.isPresent());
    assertEquals(STOCK, result.orElseThrow());
    verify(stockRepository, times(1)).findByStockCod("id");
  }

  @Test
  void findByStockCodEmpty() {
    when(stockRepository.findByStockCod("id")).thenReturn(Optional.empty());
    var result = findStockUserCase.findByStockCod("id");
    assertTrue(result.isEmpty());
    verify(stockRepository, times(1)).findByStockCod("id");
  }

  @Test
  void findAll() {
    var result = findStockUserCase.findAll();
    assertFalse(result.isEmpty());
    assertEquals(singletonList(STOCK), result);
    verify(stockRepository, times(1)).findAll();
  }

  @Test
  void findAllEmpty() {
    when(stockRepository.findAll()).thenReturn(emptyList());
    var result = findStockUserCase.findAll();
    assertTrue(result.isEmpty());
    assertEquals(emptyList(), result);
    verify(stockRepository, times(1)).findAll();
  }
}
