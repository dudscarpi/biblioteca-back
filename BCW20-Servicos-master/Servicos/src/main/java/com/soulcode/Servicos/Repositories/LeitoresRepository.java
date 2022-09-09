package com.soulcode.Servicos.Repositories;


import com.soulcode.Servicos.Models.Leitores;
import com.soulcode.Servicos.Models.Livros;
import com.soulcode.Servicos.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeitoresRepository extends JpaRepository<Leitores,Integer> {


    List<Livros> findByLivros(Optional<Livros> livros);

    Optional<Leitores> findByEmail(String email);
}
