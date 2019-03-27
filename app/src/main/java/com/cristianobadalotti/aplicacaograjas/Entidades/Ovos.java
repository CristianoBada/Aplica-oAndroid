package com.cristianobadalotti.aplicacaograjas.Entidades;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Ovos implements Serializable {
    private int codigo;
    private int quantidade;
    private String qualidade;
    private String data;
    private String incubacao;
    private String lote;
    private String tipoAve;

    public Ovos() {
        super();
        this.codigo = -1;
        this.data = "";
        this.qualidade = "";
        this.incubacao = "F";
        this.lote = "";
        this.quantidade = 0;
        this.tipoAve = "";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIncubacao() {
        return incubacao;
    }

    public void setIncubacao(String incubacao) {
        this.incubacao = incubacao;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getTipoAve() {
        return tipoAve;
    }

    public void setTipoAve(String tipoAve) {
        this.tipoAve = tipoAve;
    }
}
