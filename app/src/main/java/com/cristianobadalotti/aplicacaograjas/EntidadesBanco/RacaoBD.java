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

                    racao.setCodigoRacao(resultSet.getInt("codigo_racao"));
                    racao.setDataEntrada(resultSet.getString("data_entrada"));
                    racao.setQuantidade(resultSet.getInt("quantidade"));
                    racao.setTipoRacao(resultSet.getString("tipo_racao"));

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
        String comando = String.format("DELETE FROM racao WHERE codigo_racao=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public void salvar(Racao racao) {
        String comando = "";
        if (racao.getCodigoRacao() == -1) { ////codigo_racao, data_entrada, quantidade, codigo_corte, codigo_postura, tipo_racao)
            comando = String.format("INSERT INTO racao (data_entrada, quantidade, tipo_racao) " +
                            "VALUES ('%s', %d, '%s');",
                    racao.getDataEntrada(), racao.getQuantidade(), racao.getTipoRacao());
        } else {
            comando = String.format("UPDATE racao " +
                            "SET  data_entrada='%s', quantidade=%d, tipo_racao='%s'" +
                            " WHERE codigo_racao=%d;",
                    racao.getDataEntrada(), racao.getQuantidade(), racao.getTipoRacao(), racao.getCodigoRacao());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
