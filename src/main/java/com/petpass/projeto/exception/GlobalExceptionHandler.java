package com.petpass.projeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petpass.projeto.dto.ApiResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CamposObrigatoriosException.class)
    public ResponseEntity<ApiResponse> handleCampos(CamposObrigatoriosException e) {
        return new ResponseEntity<>(
            new ApiResponse(400, e.getMessage()),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<ApiResponse> handleSenha(SenhaInvalidaException e) {
        return new ResponseEntity<>(
            new ApiResponse(400, e.getMessage()),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(EmailJaCadastradoException.class)
    public ResponseEntity<ApiResponse> handleEmail(EmailJaCadastradoException e) {
        return new ResponseEntity<>(
            new ApiResponse(400, e.getMessage()),
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(LoginInvalidoException.class)
    public ResponseEntity<ApiResponse> handleLogin(LoginInvalidoException e) {
        return new ResponseEntity<>(
            new ApiResponse(401, e.getMessage()),
            HttpStatus.UNAUTHORIZED
        );
    }

    // erro genérico
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeral(Exception e) {
        return new ResponseEntity<>(
            new ApiResponse(500, "Erro interno do servidor"),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}