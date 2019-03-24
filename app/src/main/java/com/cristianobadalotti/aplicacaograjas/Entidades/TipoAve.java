package com.cristianobadalotti.aplicacaograjas.Entidades;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoAve extends _Default implements Serializable {

    private String nomeAve;

    private int tempoChocagem;

    public TipoAve() {
        super();
        this.nomeAve = "";
        this.tempoChocagem = 0;
    }

    public ArrayList<String> getListaAves() {
        BD bd = new BD();
        ArrayList<String> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM tipo_ave;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome_ave");

                    lista.add(nome);
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return lista;
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
