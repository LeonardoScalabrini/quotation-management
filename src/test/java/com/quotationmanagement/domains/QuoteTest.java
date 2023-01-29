package com.quotationmanagement.domains;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;
import java.util.Objects;
import org.junit.jupiter.api.Test;

class QuoteTest {

  @Test
  void test() {
    var date = new Date();
    var quote = new Quote(date, 10);
    var equals = new Quote(date, 10);
    var notEquals = new Quote(new Date(), 15);
    assertEquals(date, quote.getDate());
    assertEquals(Integer.valueOf(10), quote.getPrice());
    assertEquals(equals, quote);
    assertEquals(Objects.hash(date, 10), quote.hashCode());
    assertNotEquals(notEquals, quote);
    assertNotEquals(null, quote);
    assertNotEquals(new Object(), quote);
  }

  @Test
  void notNull() {
    assertThrows(NullPointerException.class, () -> new Quote(null, 1));
    assertThrows(NullPointerException.class, () -> new Quote(new Date(), null));
    assertThrows(NullPointerException.class, () -> new Quote(null, null));
  }
}
