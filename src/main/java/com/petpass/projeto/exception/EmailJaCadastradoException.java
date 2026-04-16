package com.petpass.projeto.exception;

// Classe de exceção personalizada
public class EmailJaCadastradoException extends RuntimeException {

    public EmailJaCadastradoException(String mensagem) {
        super(mensagem);
    }
}