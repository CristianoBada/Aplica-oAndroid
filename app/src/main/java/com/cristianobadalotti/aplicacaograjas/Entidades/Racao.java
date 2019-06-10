package com.cristianobadalotti.aplicacaograjas.Entidades;

import java.io.Serializable;

public class Racao implements Serializable {
    private int codigoRacao = -1;
    private String tipoRacao;
    private Integer quantidade;
    private String dataEntrada;
    private int codigoPostura = 0;
    private int codigoCorte = 0;

    public int getCodigoRacao() {
        return codigoRacao;
    }

    public void setCodigoRacao(int codigoRacao) {
        this.codigoRacao = codigoRacao;
    }

    public String getTipoRacao() {
        return tipoRacao;
    }

    public void setTipoRacao(String tipoRacao) {
        this.tipoRacao = tipoRacao;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public int getCodigoPostura() {
        return codigoPostura;
    }

    public void setCodigoPostura(int codigoPostura) {
        this.codigoPostura = codigoPostura;
    }

    public int getCodigoCorte() {
        return codigoCorte;
    }

    public void setCodigoCorte(int codigoCorte) {
        this.codigoCorte = codigoCorte;
    }
}
