package com.quotationmanagement.controllers;

import static java.util.stream.Collectors.*;

import com.quotationmanagement.domains.Stock;
import com.quotationmanagement.domains.exceptions.StockNotRegistredException;
import com.quotationmanagement.domains.interfaces.CreateStockUserCase;
import com.quotationmanagement.domains.interfaces.FindStockUserCase;
import com.quotationmanagement.dtos.StockRequestDTO;
import com.quotationmanagement.dtos.StockResponseDTO;
import java.text.ParseException;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/stocks")
public class StockController {

  private final FindStockUserCase findStockUserCase;
  private final CreateStockUserCase createStockUserCase;

  @Autowired
  public StockController(
      FindStockUserCase findStockUserCase, CreateStockUserCase createStockUserCase) {
    this.findStockUserCase = findStockUserCase;
    this.createStockUserCase = createStockUserCase;
  }

  @PostMapping()
  public @ResponseBody ResponseEntity save(@Valid @RequestBody StockRequestDTO stockRequestDTO) {
    try {
      Stock result =
          createStockUserCase.create(stockRequestDTO.getStockId(), stockRequestDTO.quotesValue());
      return ResponseEntity.status(HttpStatus.CREATED).body(StockResponseDTO.parseOf(result));
    } catch (StockNotRegistredException | ParseException e) {
      return ResponseEntity.internalServerError().body(e.getMessage());
    }
  }

  @GetMapping()
  public @ResponseBody ResponseEntity<List<StockResponseDTO>> list() {
    var result = findStockUserCase.findAll();
    if (result.isEmpty()) return ResponseEntity.noContent().build();
    return ResponseEntity.ok(result.stream().map(StockResponseDTO::parseOf).collect(toList()));
  }

  @GetMapping("/{stockId}")
  public @ResponseBody ResponseEntity<StockResponseDTO> findByStockId(
      @PathVariable("stockId") String stockCod) {
    var result = findStockUserCase.findByStockCod(stockCod);
    if (result.isEmpty()) return ResponseEntity.noContent().build();
    return ResponseEntity.ok(StockResponseDTO.parseOf(result.orElseThrow()));
  }
}
