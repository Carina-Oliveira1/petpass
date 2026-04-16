package com.petpass.projeto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.petpass.projeto.model.Usuario;
import com.petpass.projeto.service.UsuarioService;
import com.petpass.projeto.dto.AlterarSenhaDTO;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // cadastro
    @PostMapping("/cadastro")
    public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioService.cadastrar(usuario);
        return ResponseEntity.ok(novoUsuario);
    }

    // login 
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        Usuario usuarioLogado = usuarioService.login(usuario.getEmail(), usuario.getSenha());
        return ResponseEntity.ok(usuarioLogado);
    }

    // atualizar perfil
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario atualizado = usuarioService.atualizar(id, usuario);
        return ResponseEntity.ok(atualizado);
    }

    // atualizar apenas senha
    @PatchMapping("/{id}/senha")
    public ResponseEntity<String> alterarSenha(@PathVariable Long id, @RequestBody AlterarSenhaDTO dto) {
        usuarioService.atualizarSenha(id, dto);
        return ResponseEntity.ok("Senha atualizada com sucesso!");
    }
}