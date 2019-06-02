package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.TipoAve;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoAveBD extends _Default {
    public ArrayList<String> getListaString() {
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

    public int getTempoChocar(String nome) {
        BD bd = new BD();
        int ret = 0;
        try {
            String comando = String.format("SELECT * FROM tipo_ave WHERE nome_ave=%s;", nome);
            ResultSet resultSet = bd.select(comando);

            if (resultSet != null) {

                   ret = resultSet.getInt("tempo_chocagem");
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return ret;
    }
}
