package com.quotationmanagement.applicationEvents;

import static com.quotationmanagement.fixture.Fixture.STOCK_MANAGER_NOTIFICATION_DTO;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.quotationmanagement.configurations.ApplicationConfig;
import com.quotationmanagement.externalServices.interfaces.StockManagerService;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class StartupEventTest {

  @Mock private StockManagerService stockManagerService;
  @Mock private ApplicationConfig applicationConfig;
  @InjectMocks private StartupEvent startupEvent;

  @BeforeEach
  void setUp() {
    when(applicationConfig.getHost()).thenReturn(STOCK_MANAGER_NOTIFICATION_DTO.host);
    when(applicationConfig.getPort()).thenReturn(STOCK_MANAGER_NOTIFICATION_DTO.port);
    when(stockManagerService.notification(STOCK_MANAGER_NOTIFICATION_DTO))
        .thenReturn(Collections.singletonList(STOCK_MANAGER_NOTIFICATION_DTO));
  }

  @Test
  void afterStartup() {
    startupEvent.afterStartup();
    verify(applicationConfig, times(1)).getHost();
    verify(applicationConfig, times(1)).getPort();
    verify(stockManagerService, times(1)).notification(STOCK_MANAGER_NOTIFICATION_DTO);
  }
}
