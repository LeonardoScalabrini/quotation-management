package com.quotationmanagement.externals;

import static com.quotationmanagement.fixture.Fixture.*;
import static java.util.Collections.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.quotationmanagement.externals.interfaces.StockManagerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class StockManagerCacheServiceImplTest {

  @Mock private StockManagerService stockManagerService;
  @InjectMocks private StockManagerCacheServiceImpl stockManagerCacheService;

  @BeforeEach
  void setUp() {
    when(stockManagerService.getStocks()).thenReturn(singletonList(STOCK_MANAGER_DTO));
    when(stockManagerService.notification(any()))
        .thenReturn(singletonList(STOCK_MANAGER_NOTIFICATION_DTO));
  }

  @Test
  void getStocks() {
    stockManagerCacheService.clean();
    var result = stockManagerCacheService.getStocks();
    var result2 = stockManagerCacheService.getStocks();
    assertEquals(singletonList(STOCK_MANAGER_DTO), result);
    assertEquals(singletonList(STOCK_MANAGER_DTO), result2);
    verify(stockManagerService, times(1)).getStocks();
  }

  @Test
  void notification() {
    var result = stockManagerCacheService.notification(any());
    assertEquals(singletonList(STOCK_MANAGER_NOTIFICATION_DTO), result);
    verify(stockManagerService, times(1)).notification(any());
  }

  @Test
  void clean() {
    var result = stockManagerCacheService.getStocks();
    stockManagerCacheService.clean();
    var result2 = stockManagerCacheService.getStocks();
    assertEquals(singletonList(STOCK_MANAGER_DTO), result);
    assertEquals(singletonList(STOCK_MANAGER_DTO), result2);
    verify(stockManagerService, times(2)).getStocks();
  }
}
