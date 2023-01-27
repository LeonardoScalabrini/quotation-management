package com.quotationmanagement.externals;

import static com.quotationmanagement.fixture.Fixture.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.quotationmanagement.externals.interfaces.StockManagerService;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class FindByStockManagerTest {

  @Mock private StockManagerService stockManagerService;
  @InjectMocks private FindByStockManager findByStockManager;

  @BeforeEach
  void setUp() {
    when(stockManagerService.getStocks()).thenReturn(Collections.singletonList(STOCK_MANAGER_DTO));
  }

  @Test
  void find() {
    assertEquals(Optional.of(STOCK_MANAGER_DTO.id), findByStockManager.find(STOCK_MANAGER_DTO.id));
    assertEquals(Optional.empty(), findByStockManager.find("notEquals"));
  }
}
