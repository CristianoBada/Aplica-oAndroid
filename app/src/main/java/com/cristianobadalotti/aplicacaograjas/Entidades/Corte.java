package com.cristianobadalotti.aplicacaograjas.Entidades;

import java.io.Serializable;

public class Corte implements Serializable {

    private int codigoCorte = -1;
    private int quantidadeAves;
    private int mortalidade = 0;
    private String comentario;
    private int maximo;
    private String dataEntrada;
    private String dataSaida = "";
    private String tipoAve;

    public int getCodigoCorte() {
        return codigoCorte;
    }

    public void setCodigoCorte(int codigoCorte) {
        this.codigoCorte = codigoCorte;
    }

    public int getQuantidadeAves() {
        return quantidadeAves;
    }

    public void setQuantidadeAves(int quantidadeAves) {
        this.quantidadeAves = quantidadeAves;
    }

    public int getMortalidade() {
        return mortalidade;
    }

    public void setMortalidade(int mortalidade) {
        this.mortalidade = mortalidade;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getTipoAve() {
        return tipoAve;
    }

    public void setTipoAve(String tipoAve) {
        this.tipoAve = tipoAve;
    }
}
