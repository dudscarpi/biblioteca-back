package com.soulcode.Servicos.Controllers;

import com.soulcode.Servicos.Models.Leitores;
import com.soulcode.Servicos.Models.Livros;
import com.soulcode.Servicos.Security.Services.LeitoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("biblioteca")
public class LeitoresController {

    @Autowired
    LeitoresService leitoresService;

    @GetMapping("/leitores")
    public List<Leitores> mostrarTodosLeitores(){
        List<Leitores> leitores = leitoresService.mostrarTodosLeitores();
        return leitores;
    }


    @GetMapping("/leitores/{idLeitor}")
    public ResponseEntity<Leitores> mostrarUmLeitor(@PathVariable Integer idLeitor){
        Leitores leitores = leitoresService.mostrarUmLeitor(idLeitor);
        return ResponseEntity.ok().body(leitores);
    }

        @PostMapping("/leitores")
        public ResponseEntity<Leitores> inserirLeitor(@RequestBody Leitores leitores) {
            leitores = leitoresService.inserirLeitor(leitores);

            URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(leitores.getIdLeitor()).toUri();

            return ResponseEntity.created(novaUri).body(leitores);
        }

        @PutMapping("/leitores/{idLeitor}")
        public ResponseEntity<Leitores> editarLeitor(@PathVariable Integer idLeitor, @RequestBody Leitores leitores) {
            leitores.setIdLeitor(idLeitor);
            leitores = leitoresService.editarLeitor(leitores);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping("leitores/{idLeitor}")
        public ResponseEntity<Void> excluirLeitor(@PathVariable Integer idLeitor){
            leitoresService.excluirLeitor(idLeitor);
            return ResponseEntity.noContent().build();
        }

    @GetMapping("/leitoresPeloEmail/{email}")
    public ResponseEntity<Leitores> mostrarUmLeitorPeloEmail(@PathVariable String email){
        Leitores leitores = leitoresService.mostrarUmLeitorPeloEmail(email);
        return ResponseEntity.ok().body(leitores);
    }

//    @GetMapping("leitoresPorLivro/{idLivro}")
//    public List<Livros> mostrarTodosLeitoresDeUmLivro(@PathVariable Integer idLivro){
//        List<Livros> livros = leitoresService.mostrarTodosLeitoresDeUmLivro(idLivro);
//        return livros;
//    }
//

    }
