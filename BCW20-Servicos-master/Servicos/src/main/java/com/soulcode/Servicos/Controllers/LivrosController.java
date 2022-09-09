package com.soulcode.Servicos.Controllers;


import com.soulcode.Servicos.Models.Autores;
import com.soulcode.Servicos.Models.Emprestimo;
import com.soulcode.Servicos.Models.Livros;
import com.soulcode.Servicos.Security.Services.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("biblioteca")
public class LivrosController {

    @Autowired
    LivrosService livrosService;


    @GetMapping("/livros")
    public List<Livros> mostrarTodosLivros(){
        List<Livros> livros = livrosService.mostrarTodosLivros();
        return livros;
}
    @GetMapping("/livros/{idLivro}")
    public ResponseEntity<Livros> mostrarUmLivro(@PathVariable Integer idLivro){
        Livros livros = livrosService.mostrarUmLivro(idLivro);
        return ResponseEntity.ok().body(livros);
    }

    @PutMapping("/livros/{idLivro}")
    public ResponseEntity<Livros> editarLivro(@PathVariable Integer idLivro,
                                              @RequestBody Livros livros){
        livros.setIdLivro(idLivro);
        Livros livr = livrosService.editarLivro(livros, idLivro);
        return ResponseEntity.ok().body(livr);
    }

    @PostMapping("/livros/{idAutor}")
    public ResponseEntity<Livros> cadastrarLivro(@PathVariable Integer idAutor,
                                                 @RequestBody Livros livros){
        livros = livrosService.cadastrarLivro(livros,idAutor);
        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(livros.getIdLivro()).toUri();
        return ResponseEntity.created(novaUri).body(livros);
    }

    @DeleteMapping("/livros/{idLivro}")
    public ResponseEntity<Void> excluirLivro(@PathVariable Integer idLivro){
        livrosService.excluirLivro(idLivro);
        return ResponseEntity.noContent().build();
    }

        @GetMapping("/livrosComFotoNull")
    public List<Livros> livrosComFotoNull(){
        return livrosService.livrosComFotoNull();
    }

    @GetMapping("/livrosPeloTitulo/{titulo}")
    public List<Livros> buscarLivrosPeloTitulo(@PathVariable ("titulo")String titulo){
        List<Livros> livros = livrosService.buscarLivrosPeloTitulo(titulo);
        return livros;
    }

    @GetMapping("/livrosPeloStatus")
    List<Livros> listarLivrosPeloStatus(@RequestParam("status") String status){
        List<Livros> livros = livrosService.listarLivrosPeloStatus(status);
        return livros;
    }

    @GetMapping("/quantidadeLivros")
    List<?> quantidadeDeLivrosPorStatus(){
        return livrosService.quantidadeDeLivrosPorStatus();
    }

}
