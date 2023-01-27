package com.quotationmanagement.usercases;

import static com.quotationmanagement.fixture.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.quotationmanagement.domains.exceptions.StockNotRegistredException;
import com.quotationmanagement.domains.interfaces.CheckStockRegistredUseCase;
import com.quotationmanagement.domains.interfaces.StockRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CreateStockUserCaseImplTest {
  @Mock private StockRepository stockRepository;
  @Mock private CheckStockRegistredUseCase checkStockRegistredUseCase;
  @InjectMocks private CreateStockUserCaseImpl createStockUserCase;

  @BeforeEach
  void setUp() throws StockNotRegistredException {
    when(stockRepository.save(any())).thenReturn(STOCK);
    doNothing().when(checkStockRegistredUseCase).check(anyString());
  }

  @Test
  void create() throws StockNotRegistredException {
    var result = createStockUserCase.create(STOCK.stockCod, STOCK.quotes);
    assertNotNull(result.id);
    assertEquals(STOCK.stockCod, result.stockCod);
    assertEquals(STOCK.quotes, result.quotes);
    verify(stockRepository, times(1)).save(any());
    verify(checkStockRegistredUseCase, times(1)).check(anyString());
  }
}
