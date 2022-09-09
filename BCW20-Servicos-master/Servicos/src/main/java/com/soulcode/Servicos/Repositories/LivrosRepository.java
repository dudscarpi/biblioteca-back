package com.soulcode.Servicos.Repositories;

import com.soulcode.Servicos.Models.Autores;
import com.soulcode.Servicos.Models.Emprestimo;
import com.soulcode.Servicos.Models.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface LivrosRepository extends JpaRepository<Livros, Integer> {



    @Query(value="SELECT * FROM livros WHERE foto IS NULL;", nativeQuery = true)
    List<Livros> livrosComFotoNull();
    @Query(value = "SELECT * FROM livros WHERE titulo =:titulo",nativeQuery = true )
    List<Livros> livrosPeloTitulo(String titulo);

    @Query(value = "SELECT * FROM livros WHERE status =:status",nativeQuery = true )
    List<Livros> findByStatus(String status);

    @Query(value="SELECT COUNT(id_emprestimo), emprestimo.status FROM emprestimo GROUP BY emprestimo.status", nativeQuery = true)
    List<?> quantidadeDeLivrosPorStatus();

//    @Query(value = "SELECT * FROM livros WHERE idAutor =:idAutor",nativeQuery = true )
//    List<Autores> buscarLivrosPeloAutor(Integer idAutor);


}
