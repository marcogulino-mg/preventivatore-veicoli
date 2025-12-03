package com.marcogulino.preventivatore.preventivatore_veicoli.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcogulino.preventivatore.preventivatore_veicoli.models.Client;

@Repository
public interface ClientRepo extends JpaRepository<Client, Integer> {

}
