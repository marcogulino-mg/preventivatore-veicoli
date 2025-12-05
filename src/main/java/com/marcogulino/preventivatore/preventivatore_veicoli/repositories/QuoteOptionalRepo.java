package com.marcogulino.preventivatore.preventivatore_veicoli.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.QuoteOptional;

@Repository
public interface QuoteOptionalRepo extends JpaRepository<QuoteOptional, Integer> {

}
