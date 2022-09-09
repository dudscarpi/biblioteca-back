package com.soulcode.Servicos.Models;

public enum StatusLeitor {

    ATIVO("Ativo"),
    INATIVO("Inativo");

    private String situacao;

    StatusLeitor(String situacao) {
        this.situacao = situacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
