package com.petpass.projeto.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Usuario{
    
    //atributos da classe
    private String nome;
    private String email;
    private String telefone; //é string pra manter a formatação
    @JsonIgnore //evita que a senha seja enviada de volta para o front end
    private String senha;
    private String localizacao;
    private String fotoPerfil; //guarda só o link da imagem

    //relacionamento:; um usuário tem uma lista de animais (0..* )
    private List<Animal> animais = new ArrayList<>();

    //construtor vazio
    public Usuario(){

    }

    //métodos de regra de negócio
    public void adicionarAnimal(Animal animal){
        this.animais.add(animal);
        System.out.println("Animal adicional com sucesso!");
    }

    public void deletarAnimal(Animal animal){
        this.animais.remove(animal);
        System.out.println("Animal removido do perfil.");
    }

    public void editarPerfil(String novaLocalizacao, String novaFoto){
        this.localizacao = novaLocalizacao;
        this.fotoPerfil = novaFoto;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getTelefone(){
        return telefone;
    }

    public void setTelefone(String telefone){
        this.telefone = telefone;
    }

    public String getSenha(){
        return senha;
    }

    public void setSenha(String senha){
        this.senha = senha;
    }

    public String getLocalizacao(){
        return localizacao;
    }

    public void setLocalizacao(String localizacao){
        this.localizacao = localizacao;
    }

    public String getFotoPerfil(){
        return fotoPerfil;
    }

    public void setFotoPerfil(String fotoPerfil){
        this.fotoPerfil = fotoPerfil;
    }

    public List<Animal> getAnimais(){
        return animais;
    }

    public void setAnimais(List<Animal> animais){
        this.animais = animais;
    }

}
