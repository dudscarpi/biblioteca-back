package com.soulcode.Servicos.Security.Services;


import com.soulcode.Servicos.Models.*;
import com.soulcode.Servicos.Repositories.LeitoresRepository;
import com.soulcode.Servicos.Repositories.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeitoresService {

    @Autowired
    LeitoresRepository leitoresRepository;

    @Autowired
    LivrosRepository livrosRepository;
    @Cacheable("leitoresCache")
    public List<Leitores> mostrarTodosLeitores() {
        return leitoresRepository.findAll();
    }
    @Cacheable(value = "leitoresCache", key = "#idLeitor")
    public Leitores mostrarUmLeitor(Integer idLeitor){
        Optional<Leitores> leitores = leitoresRepository.findById(idLeitor);
        return leitores.orElseThrow();
    }

    public Leitores inserirLeitor(Leitores leitores) {
        leitores.setIdLeitor(null);
        return leitoresRepository.save(leitores);
    }
    public Leitores editarLeitor(Leitores leitores) {
        mostrarUmLeitor(leitores.getIdLeitor());
        return leitoresRepository.save(leitores);
    }
    public void excluirLeitor(Integer idLeitor) {
        mostrarUmLeitor(idLeitor);
        leitoresRepository.deleteById(idLeitor);
    }

    public Leitores mostrarUmLeitorPeloEmail(String email){
        Optional<Leitores> leitores = leitoresRepository.findByEmail(email);
        return leitores.orElseThrow();
    }


//    @Cacheable(value = "leitoresCache", key = "#idLivro")
//    public List<Livros> mostrarTodosLeitoresDeUmLivro(Integer idLivro){
//        Optional<Livros> livros = livrosRepository.findById(idLivro);
//        return leitoresRepository.findByLivros(livros);
//    }

}
