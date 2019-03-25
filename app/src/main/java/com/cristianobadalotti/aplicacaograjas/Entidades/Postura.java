package com.cristianobadalotti.aplicacaograjas.Entidades;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Postura implements Serializable {
    private int codigoPostura;
    private int quantidade;
    private String comentario;
    private int maximoAves;
    private String dataentrada;
    private String datasaida;
    private String tipoAve;

    public Postura() {
        this.codigoPostura = -1;
        this.quantidade = 0;
        this.comentario = "";
        this.maximoAves = 0;
        this.dataentrada = "";
        this.datasaida = "";
        this.tipoAve = "";
    }

    public int getCodigoPostura() {
        return codigoPostura;
    }

    public void setCodigoPostura(int codigoPostura) {
        this.codigoPostura = codigoPostura;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getMaximoAves() {
        return maximoAves;
    }

    public void setMaximoAves(int maximoAves) {
        this.maximoAves = maximoAves;
    }

    public String getDataentrada() {
        return dataentrada;
    }

    public void setDataentrada(String dataentrada) {
        this.dataentrada = dataentrada;
    }

    public String getDatasaida() {
        return datasaida;
    }

    public void setDatasaida(String datasaida) {
        this.datasaida = datasaida;
    }

    public String getTipoAve() {
        return tipoAve;
    }

    public void setTipoAve(String tipoAve) {
        this.tipoAve = tipoAve;
    }
}
