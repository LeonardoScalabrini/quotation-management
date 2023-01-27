package com.quotationmanagement.events;

import com.quotationmanagement.configurations.ApplicationConfig;
import com.quotationmanagement.dtos.StockManagerNotificationDTO;
import com.quotationmanagement.externals.interfaces.StockManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupEvent {

  private final StockManagerService stockManagerService;
  private final ApplicationConfig applicationConfig;

  @Autowired
  public StartupEvent(
      StockManagerService stockManagerService, ApplicationConfig applicationConfig) {
    this.stockManagerService = stockManagerService;
    this.applicationConfig = applicationConfig;
  }

  @EventListener(ApplicationReadyEvent.class)
  public void afterStartup() {
    stockManagerService.notification(
        new StockManagerNotificationDTO(applicationConfig.getHost(), applicationConfig.getPort()));
  }
}
