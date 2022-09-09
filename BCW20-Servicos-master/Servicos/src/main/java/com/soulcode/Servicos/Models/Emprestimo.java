package com.soulcode.Servicos.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idEmprestimo;

    @Column(nullable = true)
    private String descricao;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @Column(columnDefinition = "date", nullable = false)
    private Date dataDevolucao;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo statusEmprestimo;

    @ManyToOne
    @JoinColumn(name = "idLivro")
    private Livros livros;

    @ManyToOne
    @JoinColumn(name = "idLeitor")
    private Leitores leitores;

    public Integer getIdEmprestimo() {
        return idEmprestimo;
    }

    public void setIdEmprestimo(Integer idEmprestimo) {
        this.idEmprestimo = idEmprestimo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public StatusEmprestimo getStatusEmprestimo() {
        return statusEmprestimo;
    }

    public void setStatusEmprestimo(StatusEmprestimo statusEmprestimo) {
        this.statusEmprestimo = statusEmprestimo;
    }

    public Livros getLivros() {
        return livros;
    }

    public void setLivros(Livros livros) {
        this.livros = livros;
    }

    public Leitores getLeitores() {
        return leitores;
    }

    public void setLeitores(Leitores leitores) {
        this.leitores = leitores;
    }
}
