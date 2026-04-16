package com.petpass.projeto.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "campanhas")
public class Campanha {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipoCampanha;
    private String cidade;
    private LocalDate data;
    private String publicoAlvo;
    private String tiposVacinas;
    private String descricaoImportancia;

    public Campanha() {}

    public Boolean atendeFiltro(String cidadeBuscada, String publicoBuscado) {
        boolean cidadeBate = this.cidade != null && this.cidade.equalsIgnoreCase(cidadeBuscada);
        boolean publicoBate = this.publicoAlvo != null && this.publicoAlvo.equalsIgnoreCase(publicoBuscado);
        return cidadeBate && publicoBate;
    }

    // --- GETTERS E SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipoCampanha() { return tipoCampanha; }
    public void setTipoCampanha(String tipoCampanha) { this.tipoCampanha = tipoCampanha; }

    public String getCidade() { return cidade; }
    public void setCidade(String cidade) { this.cidade = cidade; }

    public LocalDate getData() { return data; }
    public void setData(LocalDate data) { this.data = data; }

    public String getPublicoAlvo() { return publicoAlvo; }
    public void setPublicoAlvo(String publicoAlvo) { this.publicoAlvo = publicoAlvo; }

    public String getTiposVacinas() { return tiposVacinas; }
    public void setTiposVacinas(String tiposVacinas) { this.tiposVacinas = tiposVacinas; }

    public String getDescricaoImportancia() { return descricaoImportancia; }
    public void setDescricaoImportancia(String descricaoImportancia) { this.descricaoImportancia = descricaoImportancia; }
}