package com.petpass.projeto.model;

import java.time.LocalDate;

public class Campanha{
    
    //atributos da classe
    private String tipoCampanha;
    private String cidade;
    private LocalDate data;
    private String publicoAlvo;
    private String tiposVacinas;
    private String descricaoImportancia;

    public Campanha(){

    }

    //método do filtro
    public Boolean atendeFiltro(String cidadeBuscada, String publicoBuscado){

        //verifica se a cidade da campanha e o público alvo batem com a busca do usuário
        boolean cidadeBate = this.cidade.equalsIgnoreCase(cidadeBuscada);
        boolean publicoBate = this.publicoAlvo.equalsIgnoreCase(publicoBuscado);

        return cidadeBate && publicoBate;
    }

        public String getTipoCampanha(){
            return tipoCampanha;
        }

        public void setTipoCampanha(String tipoCampanha){
            this.tipoCampanha = tipoCampanha;
        }

        public String getCidade(){
            return cidade;
        }

        public void setCidade(String cidade){
            this.cidade = cidade;
        }

        public LocalDate getData(){
            return data;
        }

        public void setData(LocalDate data){
            this.data = data;
        }

        public String getPublicoAlvo(){
            return publicoAlvo;
        }

        public void setPublicoAlvo(String publicoAlvo){
            this.publicoAlvo = publicoAlvo;
        }

        public String getTiposVacinas(){
            return tiposVacinas;
        }

        public void setTiposVacinas(String tiposVacinas){
            this.tiposVacinas = tiposVacinas;
        }

        public String getDescricaoImportancia(){
            return descricaoImportancia;
        }

        public void setDescricaoImportancia(String descricaoImportancia){
            this.descricaoImportancia = descricaoImportancia;
        }

}
