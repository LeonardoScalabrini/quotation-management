package com.quotationmanagement.externals.interfaces;

import com.quotationmanagement.dtos.StockManagerDTO;
import com.quotationmanagement.dtos.StockManagerNotificationDTO;
import java.util.List;
import org.springframework.web.client.HttpStatusCodeException;

public interface StockManagerService {

  List<StockManagerDTO> getStocks() throws HttpStatusCodeException;

  List<StockManagerNotificationDTO> notification(StockManagerNotificationDTO request);
}
