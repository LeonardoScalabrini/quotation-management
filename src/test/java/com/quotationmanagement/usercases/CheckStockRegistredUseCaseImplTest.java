package com.quotationmanagement.usercases;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.quotationmanagement.domains.exceptions.StockNotRegistredException;
import com.quotationmanagement.domains.interfaces.FindRegistredStock;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
class CheckStockRegistredUseCaseImplTest {

  @Mock private FindRegistredStock findRegistredStock;
  @InjectMocks private CheckStockRegistredUseCaseImpl checkStockRegistredUseCase;

  @BeforeEach
  void setUp() {
    when(findRegistredStock.find("id")).thenReturn(Optional.of("id"));
  }

  @Test
  void check() {
    assertDoesNotThrow(() -> checkStockRegistredUseCase.check("id"));
  }

  @Test
  void checkException() {
    when(findRegistredStock.find("id")).thenReturn(Optional.empty());
    assertThrows(StockNotRegistredException.class, () -> checkStockRegistredUseCase.check("id"));
  }
}
