package com.quotationmanagement.usercases;

import static com.quotationmanagement.fixture.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
  @InjectMocks private CreateStockUserCaseImpl createStockUserCase;

  @BeforeEach
  void setUp() {
    when(stockRepository.save(any())).thenReturn(STOCK);
  }

  @Test
  void create() {
    var result = createStockUserCase.create(STOCK.stockCod, STOCK.quotes);
    assertNotNull(result.id);
    assertEquals(STOCK.stockCod, result.stockCod);
    assertEquals(STOCK.quotes, result.quotes);
    verify(stockRepository, times(1)).save(any());
  }
}
