package com.soulcode.Servicos.Repositories;

import com.soulcode.Servicos.Models.Emprestimo;
import com.soulcode.Servicos.Models.Leitores;
import com.soulcode.Servicos.Models.Livros;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

    @Query(value = "SELECT * FROM emprestimo WHERE status =:status",nativeQuery = true )
    List<Emprestimo> findByStatus(String status);

    @Query(value="SELECT COUNT(status =:status) from emprestimo", nativeQuery = true)
    List<?> quantidadeDeEmprestimosPorStatus();


    @Query(value="SELECT * FROM emprestimo WHERE data_devolucao BETWEEN :data1 AND :data2", nativeQuery = true)
    List<Emprestimo> findByIntervaloDataDevolucao(Date data1, Date data2);


    List<Emprestimo> findByLeitores(Optional<Leitores> leitores);

}
