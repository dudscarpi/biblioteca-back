package com.soulcode.Servicos.Security.Services.Exceptions;

public class DataIntegrityViolationException  extends RuntimeException{

    public DataIntegrityViolationException(String msg){
        super(msg);
    }
}
