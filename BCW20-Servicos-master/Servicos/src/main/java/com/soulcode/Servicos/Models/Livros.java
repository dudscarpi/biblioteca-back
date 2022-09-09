package com.soulcode.Servicos.Models;

import javax.persistence.*;

@Entity
public class Livros {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLivro;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = true, length = 250)
    private String resumo;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    @Column(nullable = true)
    private String foto;

    @ManyToOne
    @JoinColumn(name = "idAutor")
    private Autores autores;

    public Integer getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(Integer idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Autores getAutores() {
        return autores;
    }

    public void setAutores(Autores autores) {
        this.autores = autores;
    }

}
