package com.soulcode.Servicos.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Leitores {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLeitor;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    private StatusLeitor statusLeitor;

    @JsonIgnore
    @OneToMany(mappedBy = "leitores")
    private List<Emprestimo> emprestimos = new ArrayList<Emprestimo>();


    @ManyToOne
    @JoinColumn(name = "idLivro")
    private Livros livros;
    public Integer getIdLeitor() {
        return idLeitor;
    }

    public void setIdLeitor(Integer idLeitor) {
        this.idLeitor = idLeitor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public StatusLeitor getStatusLeitor() {
        return statusLeitor;
    }

    public void setStatusLeitor(StatusLeitor statusLeitor) {
        this.statusLeitor = statusLeitor;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }

    public Livros getLivros() {
        return livros;
    }

    public void setLivros(Livros livros) {
        this.livros = livros;
    }
}
