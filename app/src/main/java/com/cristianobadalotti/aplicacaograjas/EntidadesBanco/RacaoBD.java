package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Racao;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;

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
                    racao.setDataEntrada(resultSet.getString("entrada"));
                    racao.setQuantidade(resultSet.getInt("quantidade"));
                    racao.setTipoRacao(resultSet.getString("tiporacao"));

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

    public void apagar(int codigo) {
        String comando = String.format("DELETE FROM racao WHERE codigo=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public void salvar(Racao racao) {
        String comando = "";
        if (racao.getCodigoRacao() == -1) { ////codigo_racao, data_entrada, quantidade, codigo_corte, codigo_postura, tipo_racao)
            comando = String.format("INSERT INTO racao (entrada, quantidade, tiporacao) " +
                            "VALUES ('%s', %d, '%s');",
                    racao.getDataEntrada(), racao.getQuantidade(), racao.getTipoRacao());
        } else {
            comando = String.format("UPDATE racao " +
                            "SET  entrada='%s', quantidade=%d, tiporacao='%s'" +
                            " WHERE codigo=%d;",
                    racao.getDataEntrada(), racao.getQuantidade(), racao.getTipoRacao(), racao.getCodigoRacao());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
