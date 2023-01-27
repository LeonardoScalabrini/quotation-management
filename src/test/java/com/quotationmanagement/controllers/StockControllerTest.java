package com.quotationmanagement.controllers;

import static com.quotationmanagement.fixture.Fixture.*;
import static java.util.Collections.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.quotationmanagement.domains.interfaces.CreateStockUserCase;
import com.quotationmanagement.domains.interfaces.FindStockUserCase;
import com.quotationmanagement.dtos.QuoteDTO;
import com.quotationmanagement.dtos.StockRequestDTO;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest
class StockControllerTest {

  @MockBean private CreateStockUserCase createStockUserCase;
  @MockBean private FindStockUserCase findStockUserCase;
  @Autowired private MockMvc mockMvc;

  @BeforeEach
  void setUp() {
    when(createStockUserCase.create(anyString(), anyList())).thenReturn(STOCK);
    when(findStockUserCase.findAll()).thenReturn(singletonList(STOCK));
    when(findStockUserCase.findByStockCod("id")).thenReturn(Optional.of(STOCK));
  }

  @Test
  void save() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/stocks")
                .content(fromJson(STOCK_REQUEST_DTO))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.stockId").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.quotes").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.quotes.[0].buyDate").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.quotes.[0].value").exists());

    verify(createStockUserCase, times(1)).create(anyString(), anyList());
  }

  @Test
  void saveBadRequest() throws Exception {
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/stocks")
                .content("")
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/stocks")
                .content(fromJson(new StockRequestDTO("", singletonList(QUOTE_DTO))))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/stocks")
                .content(
                    fromJson(new StockRequestDTO("stockId", singletonList(new QuoteDTO("", "10")))))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    mockMvc
        .perform(
            MockMvcRequestBuilders.post("/stocks")
                .content(
                    fromJson(
                        new StockRequestDTO(
                            "stockId", singletonList(new QuoteDTO("2019-01-01", "")))))
                .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    verify(createStockUserCase, times(0)).create(anyString(), anyList());
  }

  @Test
  void list() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/stocks").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].id").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].stockId").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].quotes").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].quotes.[0].buyDate").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.[0].quotes.[0].value").exists());

    verify(findStockUserCase, times(1)).findAll();
  }

  @Test
  void listNoContent() throws Exception {
    when(findStockUserCase.findAll()).thenReturn(emptyList());
    mockMvc
        .perform(MockMvcRequestBuilders.get("/stocks").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    verify(findStockUserCase, times(1)).findAll();
  }

  @Test
  void findByStockId() throws Exception {
    mockMvc
        .perform(MockMvcRequestBuilders.get("/stocks/id").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.stockId").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.quotes").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.quotes.[0].buyDate").exists())
        .andExpect(MockMvcResultMatchers.jsonPath("$.quotes.[0].value").exists());

    verify(findStockUserCase, times(1)).findByStockCod("id");
  }

  @Test
  void findByStockIdNoContent() throws Exception {
    when(findStockUserCase.findByStockCod("id")).thenReturn(Optional.empty());
    mockMvc
        .perform(MockMvcRequestBuilders.get("/stocks/id").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    verify(findStockUserCase, times(1)).findByStockCod("id");
  }
}
