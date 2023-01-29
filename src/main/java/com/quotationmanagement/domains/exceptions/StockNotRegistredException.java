package com.quotationmanagement.domains.exceptions;

public final class StockNotRegistredException extends Exception {
  public StockNotRegistredException() {
    super("The stock must be registred");
  }
}
