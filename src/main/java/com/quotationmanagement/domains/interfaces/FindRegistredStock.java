package com.quotationmanagement.domains.interfaces;

import java.util.Optional;

public interface FindRegistredStock {

  Optional<String> find(String stockId);
}
