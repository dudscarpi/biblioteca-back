package com.soulcode.Servicos.Repositories;

import com.soulcode.Servicos.Models.Autores;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AutoresRepository extends JpaRepository<Autores, Integer> {

    @Query(value="SELECT * FROM autores WHERE foto IS NULL;", nativeQuery = true)
    List<Autores> autoresComFotoNull();
    Optional<Autores> findByNome(String nome);
}
