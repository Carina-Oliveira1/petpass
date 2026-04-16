package com.petpass.projeto.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDate;
import java.time.Period;

@Entity
@Table(name = "animais")
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String foto;
    private Integer idade;
    private LocalDate dataNascimento;
    private String rg;
    private String especie;
    private String raca;
    private boolean ehCastrado;
    private boolean temMicroship;
    private String historicoVacina;
    private String classificacao;

    // chave estrangeira do banco, liga o pet ao dono.
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    @JsonIgnore // evita um loop infinito na hora de gerar o JSON no Postman
    private Usuario usuario;

    public Animal() {}

    public void calcularIdadeEClassificacao() {
        if (this.dataNascimento != null) {
            this.idade = Period.between(this.dataNascimento, LocalDate.now()).getYears();
        }

        if (this.idade != null) {
            if (this.idade < 1) {
                this.classificacao = "Filhote";
            } else if (this.idade >= 1 && this.idade < 8) {
                this.classificacao = "Adulto";
            } else {
                this.classificacao = "Idoso";
            }
        }
    }

    // --- GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }

    public Integer getIdade() { return idade; }
    public void setIdade(Integer idade) { this.idade = idade; }

    public LocalDate getDataNascimento() { return dataNascimento; }
    public void setDataNascimento(LocalDate dataNascimento) { this.dataNascimento = dataNascimento; }

    public String getRg() { return rg; }
    public void setRg(String rg) { this.rg = rg; }

    public String getEspecie() { return especie; }
    public void setEspecie(String especie) { this.especie = especie; }

    public String getRaca() { return raca; }
    public void setRaca(String raca) { this.raca = raca; }

    public boolean isEhCastrado() { return ehCastrado; }
    public void setEhCastrado(boolean ehCastrado) { this.ehCastrado = ehCastrado; }

    public boolean isTemMicroship() { return temMicroship; }
    public void setTemMicroship(boolean temMicroship) { this.temMicroship = temMicroship; }

    public String getHistoricoVacina() { return historicoVacina; }
    public void setHistoricoVacina(String historicoVacina) { this.historicoVacina = historicoVacina; }

    public String getClassificacao() { return classificacao; }
    public void setClassificacao(String classificacao) { this.classificacao = classificacao; }
}
