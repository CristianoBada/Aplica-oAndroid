package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Racao;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Conversoes;

import java.sql.ResultSet;
import java.util.ArrayList;

public class RacaoBD extends _Default {
    public ArrayList<Racao> getLista() {
        BD bd = new BD();
        ArrayList<Racao> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM racao;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    Racao racao = new Racao();

                    racao.setCodigoRacao(resultSet.getInt("codigo"));
                    racao.setDataEntrada(resultSet.getString("data2"));
                    racao.setQuantidade(resultSet.getInt("quantidade"));
                    racao.setTipoRacao(resultSet.getString("tiporacao"));
                    racao.setCodigoCorte(resultSet.getInt("corte"));
                    racao.setCodigoPostura(resultSet.getInt("postura"));

                    lista.add(racao);
                    racao = null;
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return lista;
    }


    public boolean isUserPostura( int codigo) {
        BD bd = new BD();
        ArrayList<String> lista = new ArrayList<>();

        try {
            String comando = String.format("SELECT * FROM racao WHERE postura=%d;", codigo);
            ResultSet resultSet = bd.select(comando);

            if (resultSet != null) {
                while (resultSet.next()) {
                    lista.add(resultSet.getInt("codigo") + "");
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }
        if (lista.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean isUserCorte( int codigo) {
        BD bd = new BD();
        ArrayList<String> lista = new ArrayList<>();

        try {
            String comando = String.format("SELECT * FROM racao WHERE corte=%d;", codigo);
            ResultSet resultSet = bd.select(comando);

            if (resultSet != null) {
                while (resultSet.next()) {
                    lista.add(resultSet.getInt("codigo") + "");
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }
        if (lista.size() > 0) {
            return true;
        }
        return false;
    }

    public void apagar(int codigo) {
        String comando = String.format("DELETE FROM racao WHERE codigo=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public void salvar(Racao racao) {
        String comando = "";
        String dataC = new Conversoes().convertDateBRtoDataUS(racao.getDataEntrada());
        if (racao.getCodigoRacao() == -1) {
            comando = String.format("INSERT INTO racao (data2, quantidade, tiporacao, data, corte, postura) " +
                            "VALUES ('%s', %d, '%s', '%s', %d, %d);",
                    racao.getDataEntrada(), racao.getQuantidade(), racao.getTipoRacao(), dataC,
                    racao.getCodigoCorte(), racao.getCodigoPostura());
        } else {
            comando = String.format("UPDATE racao " +
                            "SET  data2='%s', quantidade=%d, tiporacao='%s', data='%s', corte=%d, postura=%d" +
                            " WHERE codigo=%d;",
                    racao.getDataEntrada(), racao.getQuantidade(), racao.getTipoRacao(), dataC,
                    racao.getCodigoCorte(), racao.getCodigoPostura(), racao.getCodigoRacao());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
