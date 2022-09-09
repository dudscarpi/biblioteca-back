package com.soulcode.Servicos.Security.Services;

import com.soulcode.Servicos.Models.Autores;
import com.soulcode.Servicos.Models.Livros;
import com.soulcode.Servicos.Repositories.AutoresRepository;
import com.soulcode.Servicos.Repositories.LivrosRepository;
import com.soulcode.Servicos.Security.Services.Exceptions.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutoresService {
    @Autowired
    AutoresRepository autoresRepository;



    @Cacheable("autoresCache")
    public List<Autores> mostrarTodosAutores(){

        return autoresRepository.findAll();
    }
    @Cacheable(value = "autoresCache", key = "#idAutor")
    public Autores mostrarUmAutorPeloId(Integer idAutor)
    {
        Optional<Autores> autores = autoresRepository.findById(idAutor);
        return autores.orElseThrow(
                () -> new EntityNotFoundException("Autor não cadastrado: " + idAutor)
        );
    }
    @CachePut(value = "autoresCache", key = "#autores.idAutor")
    public Autores editarAutor(Autores autores){
        return autoresRepository.save(autores);
    }
    public void excluirAutor(Integer idAutor){
        autoresRepository.deleteById(idAutor);
    }

        public List<Autores> autoresComFotoNull(){
        return autoresRepository.autoresComFotoNull();
    }

        public Autores mostrarUmAutorPeloNome(String nome){
        Optional<Autores> autores = autoresRepository.findByNome(nome);
        return autores.orElseThrow();
    }

    public Autores cadastrarAutor(Autores autores) {
        autores.setIdAutor(null);
        return autoresRepository.save(autores);
    }

    @CachePut(value = "autoresCache", key = "#idAutor")
    public Autores salvarFoto(Integer idAutor, String caminhoFoto){
        Autores autores = mostrarUmAutorPeloId(idAutor);
        autores.setFoto(caminhoFoto);
        return autoresRepository.save(autores);
    }

//    @Cacheable(value = "autoresCache", key = "#idAutor")
//    public List<Autores> mostrarTodosLivrosdeUmAutor(Integer idAutor){
//        Optional<Livros> livros = livrosRepository.findById(idAutor);
//        return autoresRepository.findByLivros(livros);
//    }
//
//    public List<?> quantidadeDeLivrosPorAutor(Integer idAutor){
//        return autoresRepository.quantidadeDeLivrosPorAutor(idAutor);
//    }
}
