package com.quotationmanagement.repositories.jpa;

import com.quotationmanagement.entities.QuoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuoteJpaRepository extends JpaRepository<QuoteEntity, String> {}
