package com.quotationmanagement.externals;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.quotationmanagement.fixture.Fixture.*;
import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import com.quotationmanagement.configurations.StockManagerConfig;
import com.quotationmanagement.events.StartupEvent;
import java.io.IOException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;

@SpringBootTest
@WireMockTest(httpPort = 7777)
class StockManagerServiceImplTest {

  @Autowired public StockManagerServiceImpl stockManagerServiceImpl;

  @MockBean public StartupEvent startupEvent;
  @MockBean public StockManagerConfig stockManagerConfig;

  @BeforeEach
  void setUp() {
    doNothing().when(startupEvent).afterStartup();
    when(stockManagerConfig.getUrl()).thenReturn("http://localhost:7777");
  }

  @Test
  void getStocks() throws IOException {
    stubFor(
        get(urlEqualTo("/stock"))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.OK.value())
                    .withHeader("Content-Type", "application/json")
                    .withBody(fromJson(singletonList(STOCK_MANAGER_DTO)))));
    var result = stockManagerServiceImpl.getStocks();
    assertEquals(singletonList(STOCK_MANAGER_DTO), result);
  }

  @Test
  void getStocksEmpty() throws IOException {
    stubFor(
        get(urlEqualTo("/stock"))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.OK.value())
                    .withHeader("Content-Type", "application/json")));
    var result = stockManagerServiceImpl.getStocks();
    assertEquals(emptyList(), result);
  }

  @Test
  void getStocksWithInternalServerError() {
    stubFor(
        get(urlEqualTo("/stock"))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .withHeader("Content-Type", "application/json")));
    assertThrows(HttpStatusCodeException.class, () -> stockManagerServiceImpl.getStocks());
  }

  @Test
  void getStocksWithNotFound() {
    stubFor(
        get(urlEqualTo("/stock"))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.NOT_FOUND.value())
                    .withHeader("Content-Type", "application/json")));
    assertThrows(HttpStatusCodeException.class, () -> stockManagerServiceImpl.getStocks());
  }

  @Test
  void notification() throws IOException {
    stubFor(
        post(urlEqualTo("/notification"))
            .withRequestBody(equalToJson(fromJson(STOCK_MANAGER_NOTIFICATION_DTO)))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.OK.value())
                    .withHeader("Content-Type", "application/json")
                    .withBody(fromJson(singletonList(STOCK_MANAGER_NOTIFICATION_DTO)))));
    var result = stockManagerServiceImpl.notification(STOCK_MANAGER_NOTIFICATION_DTO);
    assertEquals(singletonList(STOCK_MANAGER_NOTIFICATION_DTO), result);
  }

  @Test
  void notificationEmpty() throws IOException {
    stubFor(
        post(urlEqualTo("/notification"))
            .withRequestBody(equalToJson(fromJson(STOCK_MANAGER_NOTIFICATION_DTO)))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.OK.value())
                    .withHeader("Content-Type", "application/json")));
    var result = stockManagerServiceImpl.notification(STOCK_MANAGER_NOTIFICATION_DTO);
    assertEquals(emptyList(), result);
  }

  @Test
  void notificationInternalServerError() throws IOException {
    stubFor(
        post(urlEqualTo("/notification"))
            .withRequestBody(equalToJson(fromJson(STOCK_MANAGER_NOTIFICATION_DTO)))
            .willReturn(
                aResponse()
                    .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .withHeader("Content-Type", "application/json")
                    .withBody(fromJson(singletonList(STOCK_MANAGER_NOTIFICATION_DTO)))));
    assertThrows(
        HttpStatusCodeException.class,
        () -> stockManagerServiceImpl.notification(STOCK_MANAGER_NOTIFICATION_DTO));
  }
}
