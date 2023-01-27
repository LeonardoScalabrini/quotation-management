package com.quotationmanagement.externalServices;

import static com.quotationmanagement.fixture.Fixture.*;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class StockManagerServiceCacheTest {

  @Mock private StockManagerServiceImpl stockManagerService;
  @InjectMocks private StockManagerServiceCache stockManagerServiceCache;

  @BeforeEach
  void setUp() {
    when(stockManagerService.getStocks()).thenReturn(singletonList(STOCK_MANAGER_DTO));
    when(stockManagerService.notification(any()))
        .thenReturn(singletonList(STOCK_MANAGER_NOTIFICATION_DTO));
  }

  @Test
  void getStocks() {
    var result = stockManagerServiceCache.getStocks();
    var result2 = stockManagerServiceCache.getStocks();
    assertEquals(singletonList(STOCK_MANAGER_DTO), result);
    assertEquals(singletonList(STOCK_MANAGER_DTO), result2);
    verify(stockManagerService, times(1)).getStocks();
  }

  @Test
  void notification() {
    var result = stockManagerServiceCache.notification(any());
    assertEquals(singletonList(STOCK_MANAGER_NOTIFICATION_DTO), result);
    verify(stockManagerService, times(1)).notification(any());
  }
}
