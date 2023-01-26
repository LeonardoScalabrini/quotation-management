package com.quotationmanagement.controllers;

import com.quotationmanagement.domains.interfaces.CreateStockUserCase;
import com.quotationmanagement.domains.interfaces.FindStockUserCase;
import com.quotationmanagement.dtos.StockRequestDTO;
import com.quotationmanagement.dtos.StockResponseDTO;
import java.text.ParseException;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
  public @ResponseBody ResponseEntity<StockResponseDTO> save(
      @Valid @RequestBody StockRequestDTO stockRequestDTO) throws ParseException {
    var result =
        createStockUserCase.create(stockRequestDTO.getStockId(), stockRequestDTO.quotesValue());
    return ResponseEntity.ok(StockResponseDTO.parseOf(result));
  }

  @GetMapping()
  public @ResponseBody ResponseEntity<List<StockResponseDTO>> list() {
    var result = findStockUserCase.findAll();
    if (result.isEmpty()) return ResponseEntity.noContent().build();
    return ResponseEntity.ok(
        result.stream().map(StockResponseDTO::parseOf).collect(Collectors.toList()));
  }

  @GetMapping("/{stockId}")
  public @ResponseBody ResponseEntity<StockResponseDTO> findByStockId(
      @PathVariable("stockId") String stockCod) {
    var result = findStockUserCase.findByStockCod(stockCod);
    if (result.isEmpty()) return ResponseEntity.noContent().build();
    return ResponseEntity.ok(StockResponseDTO.parseOf(result.orElseThrow()));
  }
}
