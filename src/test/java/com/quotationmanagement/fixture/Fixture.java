package com.quotationmanagement.fixture;

import static java.util.Arrays.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.quotationmanagement.domains.Quote;
import com.quotationmanagement.domains.Stock;
import com.quotationmanagement.dtos.*;
import java.io.IOException;
import java.util.Collections;
import org.assertj.core.util.DateUtil;

public class Fixture {

  public static final String QUOTE_PATH =
      "src/test/java/com/quotationmanagement/resources/quote.json";
  public static final String STOCK_RESPONSE_PATH =
      "src/test/java/com/quotationmanagement/resources/stock-response.json";
  public static final String STOCK_REQUEST_PATH =
      "src/test/java/com/quotationmanagement/resources/stock-request.json";
  public static final String STOCK_MANAGER_STOCK_PATH =
      "src/test/java/com/quotationmanagement/resources/stock-manager-stock.json";

  public static final String STOCK_MANAGER_NOTIFICATION_PATH =
      "src/test/java/com/quotationmanagement/resources/stock-manager-notification.json";
  public static final QuoteDTO QUOTE_DTO = QuoteDTO.parseOf(DateUtil.parse("2019-01-01"), 10);
  public static final StockResponseDTO STOCK_RESPONSE_DTO =
      new StockResponseDTO("id", "stockid", asList(QUOTE_DTO, QUOTE_DTO, QUOTE_DTO));
  public static final StockRequestDTO STOCK_REQUEST_DTO =
      new StockRequestDTO("stockid", asList(QUOTE_DTO, QUOTE_DTO, QUOTE_DTO));
  public static final StockManagerDTO STOCK_MANAGER_DTO = new StockManagerDTO("petr7", "test petr");

  public static final StockManagerNotificationDTO STOCK_MANAGER_NOTIFICATION_DTO =
      new StockManagerNotificationDTO("localhost", 8081);
  public static final Quote QUOTE = new Quote(DateUtil.parse("2019-01-01"), 10);
  public static final Stock STOCK =
      Stock.valueOf("id", "stockId", Collections.singletonList(QUOTE));

  public static <T> String fromJson(T anObject) throws IOException {
    ObjectMapper mapper = new ObjectMapper();
    ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
    return ow.writeValueAsString(anObject);
  }
}
