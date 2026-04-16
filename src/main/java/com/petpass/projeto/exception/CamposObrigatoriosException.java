package com.petpass.projeto.exception;

public class CamposObrigatoriosException extends RuntimeException {

    public CamposObrigatoriosException(String mensagem) {
        super(mensagem);
    }
}