package com.petpass.projeto.model;

import jakarta.persistence.*; 
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuarios")
public class Usuario {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 💡 Auto-incremento do ID
    private Long id;

    private String nome;
    private String email;
    private String telefone;
    
    @JsonIgnore
    private String senha;
    
    private String localizacao;
    private String fotoPerfil;

    // relacionamento no banco de dados (1 Tutor -> N Animais)
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Animal> animais = new ArrayList<>();

    public Usuario() {}

    public void adicionarAnimal(Animal animal) {
        this.animais.add(animal);
        animal.setUsuario(this); // liga o pet ao tutor
    }

    public void deletarAnimal(Animal animal) {
        this.animais.remove(animal);
        animal.setUsuario(null);
    }

    // --- GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public String getLocalizacao() { return localizacao; }
    public void setLocalizacao(String localizacao) { this.localizacao = localizacao; }

    public String getFotoPerfil() { return fotoPerfil; }
    public void setFotoPerfil(String fotoPerfil) { this.fotoPerfil = fotoPerfil; }

    public List<Animal> getAnimais() { return animais; }
    public void setAnimais(List<Animal> animais) { this.animais = animais; }
}