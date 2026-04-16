package com.petpass.projeto.exception;

public class LoginInvalidoException extends RuntimeException {

    public LoginInvalidoException(String mensagem) {
        super(mensagem);
    }
}