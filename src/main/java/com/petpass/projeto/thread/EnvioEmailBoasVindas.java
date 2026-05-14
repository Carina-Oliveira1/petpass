package com.petpass.projeto.thread;

import com.petpass.projeto.model.Usuario;

// a classe herda de Thread para rodar em paralelo
public class EnvioEmailBoasVindas extends Thread {

    private Usuario usuario;

    public EnvioEmailBoasVindas(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public void run() {
        try {
            System.out.println("[THREAD] 📧 Iniciando preparo do e-mail para: " + usuario.getEmail());
            
            // simula o tempo que um servidor SMTP levaria para enviar um e-mail (5 segundos)
            Thread.sleep(5000); 
            
            System.out.println("[THREAD] E-mail de boas-vindas enviado com sucesso para: " + usuario.getNome() + "!");
        } catch (InterruptedException e) {
            System.err.println("[THREAD] Erro ao enviar o e-mail de boas-vindas.");
        }
    }
}