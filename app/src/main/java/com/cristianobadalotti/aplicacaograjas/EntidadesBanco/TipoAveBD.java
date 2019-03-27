package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;

import java.sql.ResultSet;
import java.util.ArrayList;

public class TipoAveBD extends _Default {
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
}
