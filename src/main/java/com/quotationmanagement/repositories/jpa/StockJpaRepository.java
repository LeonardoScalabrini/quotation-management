package com.quotationmanagement.repositories.jpa;

import com.quotationmanagement.entities.StockEntity;
import com.quotationmanagement.entities.StockId;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockJpaRepository extends JpaRepository<StockEntity, StockId> {
  Optional<StockEntity> findByStockCod(String stockCod);
}