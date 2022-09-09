package com.soulcode.Servicos.Controllers;

import com.soulcode.Servicos.Models.Autores;
import com.soulcode.Servicos.Security.Services.AutoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("biblioteca")
public class AutoresController {

    @Autowired
    AutoresService autoresService;


    @GetMapping("/autores")
    public List<Autores> mostrarTodosAutores(){
        List<Autores> autores = autoresService.mostrarTodosAutores();
        return autores;
    }

    @GetMapping("/autores/{idAutor}")
    public ResponseEntity<Autores> mostrarUmAutorPeloId(@PathVariable Integer idAutor){
        Autores autores = autoresService.mostrarUmAutorPeloId(idAutor);
        return ResponseEntity.ok().body(autores);
    }

    @PutMapping("/autores/{idAutor}")
    public ResponseEntity<Autores> editarAutor(@PathVariable Integer idAutor, @RequestBody Autores autores) {
        autores.setIdAutor(idAutor);
        autoresService.editarAutor(autores);
        return ResponseEntity.ok().body(autores);
    }

    @DeleteMapping("/autores/{idAutor}")
    public ResponseEntity<Void> excluirAutor(@PathVariable Integer idAutor){
        autoresService.excluirAutor(idAutor);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/autoresPeloNome/{nome}")
    public ResponseEntity<Autores> mostrarUmAutorPeloNome(@PathVariable String nome){
        Autores autores = autoresService.mostrarUmAutorPeloNome(nome);
        return ResponseEntity.ok().body(autores);
    }

    @GetMapping("/autoresComFotoNull")
    public List<Autores> autoresComFotoNull(){
        return autoresService.autoresComFotoNull();
    }

    @PostMapping("/autores")
    public ResponseEntity<Autores> cadastrarAutor(@RequestBody Autores autores) {
        autores = autoresService.cadastrarAutor(autores);

        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(autores.getIdAutor()).toUri();

        return ResponseEntity.created(novaUri).body(autores);
    }

//
//    @GetMapping("/livrosPorAutor/{idAutor}")
//    public List<Autores> mostrarTodosLivrosdeUmAutor(@PathVariable Integer idAutor){
//        List<Autores> livros = autoresService.mostrarTodosLivrosdeUmAutor(idAutor);
//        return livros;
//    }
//
//    @GetMapping("/quantidadeLivros/{idAutor}")
//    List<?> quantidadeDeLivrosPorAutor(Integer idAutor){
//        return autoresService.quantidadeDeLivrosPorAutor(idAutor);
//    }
}
