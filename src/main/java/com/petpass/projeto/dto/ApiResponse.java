package com.petpass.projeto.dto;

import java.time.LocalDateTime;

public class ApiResponse {

    private int status;
    private String mensagem;
    private LocalDateTime timestamp;

    public ApiResponse(int status, String mensagem) {
        this.status = status;
        this.mensagem = mensagem;
        this.timestamp = LocalDateTime.now();
    }

    // getters
    public int getStatus() {
        return status;
    }

    public String getMensagem() {
        return mensagem;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}