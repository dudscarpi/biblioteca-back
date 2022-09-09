package com.soulcode.Servicos.Security.Services;

import com.soulcode.Servicos.Models.*;
import com.soulcode.Servicos.Repositories.EmprestimoRepository;
import com.soulcode.Servicos.Repositories.LeitoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {
    @Autowired
    EmprestimoRepository emprestimoRepository;

    @Autowired
    LeitoresRepository leitoresRepository;

    @Cacheable("emprestimoCache")
    public List<Emprestimo> mostrarTodosEmprestimos() {
        return emprestimoRepository.findAll();
    }
    public Emprestimo mostrarUmEmprestimo(Integer idEmprestimo) {
        Optional<Emprestimo> emprestimo = emprestimoRepository.findById(idEmprestimo);
        return emprestimo.orElseThrow();
    }

    public void excluirEmprestimo(Integer idEmprestimo) {
        emprestimoRepository.deleteById(idEmprestimo);
    }

    public Emprestimo cadastrarEmprestimo(Emprestimo emprestimo) {
        emprestimo.setStatusEmprestimo(StatusEmprestimo.DISPONIVEL);
        emprestimo.setIdEmprestimo(null);
        emprestimo.setLeitores(null);
        emprestimo.setLivros(null);
        return emprestimoRepository.save(emprestimo);
    }

//    @Cacheable(value = "emprestimoCache", key = "#status")
//    public List<Emprestimo> buscarEmprestimosPeloStatus(String status) {
//        return emprestimoRepository.findByStatus(status);
//    }
//    @Cacheable(value = "emprestimoCache", key = "#status")
//    public List<?> quantidadeDeEmprestimosPorStatus() {
//        return emprestimoRepository.quantidadeDeEmprestimosPorStatus();
//    }
//    @CachePut(value = "emprestimoCache", key = "#status")
//    public Emprestimo modificarStatus(Integer idEmprestimo,String status){
//        Emprestimo emprestimo = mostrarUmEmprestimo(idEmprestimo);
//        switch (status){
//            case "DISPONIVEL":
//            {
//                emprestimo.setStatusEmprestimo(StatusEmprestimo.EMPRESTADO);
//                break;
//            }
//            case "EMPRESTADO":
//            {
//                emprestimo.setStatusEmprestimo(StatusEmprestimo.DISPONIVEL);
//                break;
//            }
//
//        }
//        return emprestimoRepository.save(emprestimo);
//    }
//    @CachePut(value = "emprestimoCache", key = "#idLeitor")
//        public Emprestimo atribuirLeitor(Integer idEmprestimo, Integer idLeitor){
//
//        Optional<Leitores> leitores = leitoresRepository.findById(idLeitor);
//
//        Emprestimo emprestimo = mostrarUmEmprestimo(idEmprestimo);
//        emprestimo.setLeitores(leitores.get());
//        emprestimo.setStatusEmprestimo(StatusEmprestimo.EMPRESTADO);
//
//        return emprestimoRepository.save(emprestimo);
//    }
//    @Cacheable(value = "emprestimoCache", key = "T(java.util.Objects).hash(#data1, #data2)")
//    public List<Emprestimo> buscarPorIntervaloDataEntrada(Date data1, Date data2){
//        return emprestimoRepository.findByIntervaloDataEntrada(data1,data2);
//    }
//    @Cacheable(value = "emprestimoCache", key = "T(java.util.Objects).hash(#data1, #data2)")
//    public List<Emprestimo> buscarPorIntervaloDataDevolucao(Date data1, Date data2){
//        return emprestimoRepository.findByIntervaloDataDevolucao(data1,data2);
//    }
//    @Cacheable(value = "emprestimoCache", key = "#idLeitor")
//    public List<Emprestimo> buscarEmprestimoPorLeitor(Integer idLeitor){
//        Optional<Leitores> leitores = leitoresRepository.findById(idLeitor);
//        return emprestimoRepository.findByLeitores(leitores);
//    }

}
