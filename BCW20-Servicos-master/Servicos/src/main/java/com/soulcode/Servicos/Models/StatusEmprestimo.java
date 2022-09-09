package com.soulcode.Servicos.Models;

public enum StatusEmprestimo {

    DISPONIVEL("Disponivel"),
    EMPRESTADO("Emprestado");

    private String status;

    StatusEmprestimo(String status){
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
