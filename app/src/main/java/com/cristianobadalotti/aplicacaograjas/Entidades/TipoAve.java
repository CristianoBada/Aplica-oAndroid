package com.cristianobadalotti.aplicacaograjas.Entidades;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoAve implements Serializable {

    private String nomeAve;

    private int tempoChocagem;

    public TipoAve() {
        super();
        this.nomeAve = "";
        this.tempoChocagem = 0;
    }

    public String getNomeAve() {
        return nomeAve;
    }

    public void setNomeAve(String nomeAve) {
        this.nomeAve = nomeAve;
    }

    public int getTempoChocagem() {
        return tempoChocagem;
    }

    public void setTempoChocagem(int tempoChocagem) {
        this.tempoChocagem = tempoChocagem;
    }
}
