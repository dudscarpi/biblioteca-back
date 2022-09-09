package com.soulcode.Servicos.Controllers;

import com.soulcode.Servicos.Security.Services.LivrosService;
import com.soulcode.Servicos.Util.UploadFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("biblioteca")
public class UploadFileLivroController {

    @Autowired
    LivrosService livrosService;

//    @PostMapping("/livros/envioFoto/{idLivro}")
//    public ResponseEntity<Void> enviarFoto(@PathVariable Integer idLivro,
//                                           MultipartFile file,
//                                           @RequestParam("nome") String nome){
//
//        String fileName = nome;
//        String uploadDir = "/home/tatiana/Downloads/fotoFunc";
//        String nomeMaisCaminho = "/home/tatiana/Downloads/fotoFunc/" + nome;
//
//
//        try{
//            UploadFile.saveFile(uploadDir, fileName, file);
//            livrosService.salvarFoto(idLivro, nomeMaisCaminho);
//        } catch (IOException e) {
//            System.out.println("O arquivo não foi enviado: " + e.getMessage());
//        }
//        return ResponseEntity.ok().build();
//
//    }
}
