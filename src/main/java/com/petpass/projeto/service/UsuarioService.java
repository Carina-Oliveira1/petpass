package com.petpass.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.petpass.projeto.model.Usuario;
import com.petpass.projeto.repository.UsuarioRepository;
import com.petpass.projeto.exception.*;
import com.petpass.projeto.dto.AlterarSenhaDTO; // Nosso DTO de proteção

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Usuario cadastrar(Usuario usuario) {
        try {
            // RN07 
            if (usuario == null ||
                usuario.getNome() == null || usuario.getNome().trim().isEmpty() ||
                usuario.getEmail() == null || usuario.getEmail().trim().isEmpty() ||
                usuario.getSenha() == null || usuario.getSenha().trim().isEmpty() ||
                usuario.getTelefone() == null || usuario.getTelefone().trim().isEmpty() ||
                usuario.getLocalizacao() == null || usuario.getLocalizacao().trim().isEmpty()) {
                throw new CamposObrigatoriosException("Todos os campos são obrigatórios!");
            }

            // RN06 - Senha mínima
            if (usuario.getSenha().length() < 6) {
                throw new SenhaInvalidaException("A senha deve ter no mínimo 6 caracteres!");
            }

            // RN08 - Email único
            Optional<Usuario> existente = usuarioRepository.findByEmail(usuario.getEmail());
            if (existente.isPresent()) {
                throw new EmailJaCadastradoException(
                    "Este e-mail já está cadastrado. Tente fazer login ou recuperar sua senha."
                );
            }

            // criptografa antes de salvar
            usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
            return usuarioRepository.save(usuario);

        } catch (CamposObrigatoriosException | SenhaInvalidaException | EmailJaCadastradoException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro interno ao cadastrar usuário.");
        }
    }

    public Usuario login(String email, String senha) {
        try {
            Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);

            // RN09 - Não revelar se email existe ou se foi a senha
            // verifica a existência E usa o matches() na mesma linha para proteger a lógica
            if (usuarioOpt.isEmpty() || !passwordEncoder.matches(senha, usuarioOpt.get().getSenha())) {
                throw new LoginInvalidoException("Email ou senha inválidos!");
            }

            return usuarioOpt.get();

        } catch (LoginInvalidoException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Erro interno no login.");
        }
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setTelefone(usuarioAtualizado.getTelefone());
        usuario.setLocalizacao(usuarioAtualizado.getLocalizacao());

        return usuarioRepository.save(usuario);
    }

    public void atualizarSenha(Long id, AlterarSenhaDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        // verifica a senha antiga usando BCrypt
        if (!passwordEncoder.matches(dto.senhaAtual(), usuario.getSenha())) {
            throw new SenhaInvalidaException("Senha atual incorreta!");
        }

        // salva a nova senha criptografada
        usuario.setSenha(passwordEncoder.encode(dto.novaSenha()));
        usuarioRepository.save(usuario);
    }
}