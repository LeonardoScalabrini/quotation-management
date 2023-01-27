package com.quotationmanagement.domains.exceptions;

public class StockNotRegistredException extends Exception {
  public StockNotRegistredException() {
    super("The stock must be registred");
  }
}
