package com.soulcode.Servicos.Controllers;


import com.soulcode.Servicos.Models.Emprestimo;
import com.soulcode.Servicos.Security.Services.EmprestimoService;
import com.soulcode.Servicos.Security.Services.LeitoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("biblioteca")
public class EmprestimoController {

    @Autowired
    EmprestimoService emprestimoService;

    @Autowired
    LeitoresService leitoresService;

    @GetMapping("/emprestimos")
   public List<Emprestimo> mostrarTodosEmprestimos(){
        List<Emprestimo> emprestimo = emprestimoService.mostrarTodosEmprestimos();
        return emprestimo;
   }

    @GetMapping("/emprestimos/{idEmprestimo}")
    public ResponseEntity<Emprestimo> mostrarUmEmprestimo(@PathVariable Integer idEmprestimo){
        Emprestimo emprestimo = emprestimoService.mostrarUmEmprestimo(idEmprestimo);
        return ResponseEntity.ok().body(emprestimo);
    }

    @DeleteMapping("/emprestimos/{idEmprestimo}")
    public ResponseEntity<Void> excluirEmprestimo(@PathVariable Integer idEmprestimo){
        emprestimoService.excluirEmprestimo(idEmprestimo);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/emprestimos")
    public ResponseEntity<Emprestimo> cadastrarEmprestimo(@RequestBody Emprestimo emprestimo){
        emprestimo = emprestimoService.cadastrarEmprestimo(emprestimo);
        emprestimo.setDataDevolucao(new Date());

        URI novaUri = ServletUriComponentsBuilder.fromCurrentRequest().path("{/id}")
                .buildAndExpand(emprestimo.getIdEmprestimo()).toUri();
        return ResponseEntity.created(novaUri).body(emprestimo);
    }

//    @GetMapping("/emprestimosPeloStatus")
//    public List<Emprestimo> buscarEmprestimosPeloStatus(@RequestParam("status") String status){
//        List<Emprestimo> emprestimos = emprestimoService.buscarEmprestimosPeloStatus(status);
//        return emprestimos;
//    }
//
//    @GetMapping("/quantidadeEmprestimos/")
//    List<?> quantidadeDeEmprestimosPorStatus(){
//        return emprestimoService.quantidadeDeEmprestimosPorStatus();
//    }
//
//    @PutMapping("/emprestimosModificarStatus/{idEmprestimo}")
//    public ResponseEntity<Emprestimo> modificarStatus(@PathVariable Integer idEmprestimo,
//                                                   @RequestParam("status") String status){
//        emprestimoService.modificarStatus(idEmprestimo,status);
//        return ResponseEntity.noContent().build();
//    }
//
//    @PutMapping("/emprestimoAtribuirLeitor/{idEmprestimo}/{idLeitor}")
//    public ResponseEntity<Emprestimo> atribuirLeitor(@PathVariable Integer idEmprestimo,
//                                                          @PathVariable Integer idLeitor){
//        emprestimoService.atribuirLeitor(idEmprestimo, idLeitor);
//        return ResponseEntity.noContent().build();
//    }
//
//    @GetMapping("/emprestimosPorIntervaloDataEntrada")
//    public List<Emprestimo> buscarPorIntervaloDataEntrada(@RequestParam("data1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data1,
//                                                @RequestParam("data2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data2){
//        List<Emprestimo> emprestimos = emprestimoService.buscarPorIntervaloDataEntrada(data1,data2);
//        return emprestimos;
//    }
//
//    @GetMapping("/emprestimosPorIntervaloDataDevolucao")
//    public List<Emprestimo> buscarPorIntervaloDataDevolucao(@RequestParam("data1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data1,
//                                                          @RequestParam("data2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date data2){
//        List<Emprestimo> emprestimos = emprestimoService.buscarPorIntervaloDataDevolucao(data1,data2);
//        return emprestimos;
//    }
//
//    @GetMapping("/emprestimoPorLeitor/{idLeitor}")
//    public List<Emprestimo> buscarEmprestimoPorLeitor(@PathVariable Integer idLeitor){
//        List<Emprestimo> emprestimos = emprestimoService.buscarEmprestimoPorLeitor(idLeitor);
//        return emprestimos;
//    }
}
