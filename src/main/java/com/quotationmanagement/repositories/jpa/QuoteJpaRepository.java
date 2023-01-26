package com.quotationmanagement.repositories.jpa;

import com.quotationmanagement.entities.QuoteEntity;
import com.quotationmanagement.entities.StockId;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteJpaRepository extends JpaRepository<QuoteEntity, String> {
  List<QuoteEntity> findAllByStockId(StockId stockId);
}
