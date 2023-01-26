package com.quotationmanagement.fixture;

import static java.util.Arrays.*;

import com.quotationmanagement.dtos.QuoteDTO;
import com.quotationmanagement.dtos.StockRequestDTO;
import com.quotationmanagement.dtos.StockResponseDTO;
import org.assertj.core.util.DateUtil;

public class Fixture {

  public static final String QUOTE_PATH =
      "src/test/java/com/quotationmanagement/resources/quote.json";
  public static final String STOCK_RESPONSE_PATH =
      "src/test/java/com/quotationmanagement/resources/stock-response.json";
  public static final String STOCK_REQUEST_PATH =
      "src/test/java/com/quotationmanagement/resources/stock-request.json";
  public static final QuoteDTO QUOTE_DTO = QuoteDTO.parseOf(DateUtil.parse("2019-01-01"), 10);
  public static final StockResponseDTO STOCK_RESPONSE_DTO =
      new StockResponseDTO("id", "stockid", asList(QUOTE_DTO, QUOTE_DTO, QUOTE_DTO));
  public static final StockRequestDTO STOCK_REQUEST_DTO =
      new StockRequestDTO("stockid", asList(QUOTE_DTO, QUOTE_DTO, QUOTE_DTO));
}