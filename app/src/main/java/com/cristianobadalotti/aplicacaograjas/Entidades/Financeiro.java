package com.cristianobadalotti.aplicacaograjas.Entidades;

import java.io.Serializable;

public class Financeiro implements Serializable {
    private int codigoFinanceiro = -1;
    private String nome;
    private Double valor;
    private String detalhe;
    private String entrasaida;

    public int getCodigoFinanceiro() {
        return codigoFinanceiro;
    }

    public void setCodigoFinanceiro(int codigoFinanceiro) {
        this.codigoFinanceiro = codigoFinanceiro;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public String getEntrasaida() {
        return entrasaida;
    }

    public void setEntrasaida(String entrasaida) {
        this.entrasaida = entrasaida;
    }
}
