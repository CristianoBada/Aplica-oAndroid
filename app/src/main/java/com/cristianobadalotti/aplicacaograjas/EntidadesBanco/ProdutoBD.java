package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;

import java.sql.ResultSet;
import java.util.ArrayList;

public class ProdutoBD extends _Default {
    public ArrayList<String> getListaString() {
        BD bd = new BD();
        ArrayList<String> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM produtos;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    String nome = resultSet.getString("nome_produto");

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
