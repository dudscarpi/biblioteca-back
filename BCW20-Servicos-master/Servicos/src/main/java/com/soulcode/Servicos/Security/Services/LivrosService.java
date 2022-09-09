package com.soulcode.Servicos.Security.Services;

import com.soulcode.Servicos.Models.Autores;
import com.soulcode.Servicos.Models.Emprestimo;
import com.soulcode.Servicos.Models.Livros;
import com.soulcode.Servicos.Models.StatusEmprestimo;
import com.soulcode.Servicos.Repositories.AutoresRepository;
import com.soulcode.Servicos.Repositories.LeitoresRepository;
import com.soulcode.Servicos.Repositories.LivrosRepository;
import com.soulcode.Servicos.Security.Services.Exceptions.DataIntegrityViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {
    @Autowired
    LivrosRepository livrosRepository;

    @Autowired
    AutoresRepository autoresRepository;

    @Autowired
    LeitoresRepository leitoresRepository;

    @Cacheable("livrosCache")
    public List<Livros> mostrarTodosLivros(){
        return livrosRepository.findAll();	}

    @Cacheable(value = "livrosCache", key = "#idLivro")
    public Livros mostrarUmLivro(Integer idLivro) {
        Optional<Livros> livros = livrosRepository.findById(idLivro);
        return livros.orElseThrow();
    }

    public Livros editarLivro(Livros livros, Integer idLivro){
        Livros livrosSemAsNovasAlteracoes = mostrarUmLivro(idLivro);
        Autores autores = livrosSemAsNovasAlteracoes.getAutores();

        livros.setAutores(autores);
        return livrosRepository.save(livros);
    }

    public void excluirLivro(Integer idLivro){
        livrosRepository.deleteById(idLivro);
    }
    public Livros cadastrarLivro(Livros livros, Integer idAutor) throws DataIntegrityViolationException {
        livros.setIdLivro(null);
        livros.setStatus(StatusEmprestimo.DISPONIVEL);
        Optional<Autores> autores = autoresRepository.findById(idAutor);
        livros.setAutores(autores.get());

        return livrosRepository.save(livros);
    }


    public List<Livros> livrosComFotoNull(){
        return livrosRepository.livrosComFotoNull();
    }

    public List<Livros> buscarLivrosPeloTitulo(String titulo){
        return livrosRepository.livrosPeloTitulo(titulo);
    }

    public List<Livros> listarLivrosPeloStatus(String status){
        return livrosRepository.findByStatus(status);
    }

    public List<?> quantidadeDeLivrosPorStatus(){
        return livrosRepository.quantidadeDeLivrosPorStatus();
    }

}
