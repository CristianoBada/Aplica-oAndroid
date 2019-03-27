package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Ovos;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OvosBD extends _Default {

    public ArrayList<Ovos> getLista() {
        BD bd = new BD();
        ArrayList<Ovos> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM ovos;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    Ovos ovos = new Ovos();

                    ovos.setCodigo(resultSet.getInt("codigo"));
                    ovos.setData(resultSet.getString("data"));
                    ovos.setIncubacao(resultSet.getString("incubacao"));
                    ovos.setLote(resultSet.getString("lote"));
                    ovos.setQualidade(resultSet.getString("qualidade"));
                    ovos.setQuantidade(resultSet.getInt("quantidade"));
                    ovos.setTipoAve(resultSet.getString("tipo_ave"));

                    lista.add(ovos);
                    ovos = null;
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return lista;
    }

    public void apagar(int codigo) {
        String comando = String.format("DELETE FROM ovos WHERE codigo=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public void salvar(Ovos ovos) {
        String comando = "";
        if (ovos.getCodigo() == -1) {
            comando = String.format("INSERT INTO ovos (data, lote, qualidade, quantidade, tipo_ave, incubacao) VALUES ('%s', '%s', '%s', %d, '%s', '%s');",
                    ovos.getData(), ovos.getLote(), ovos.getQualidade(), ovos.getQuantidade(), ovos.getTipoAve(),
                    ovos.getIncubacao());
        } else {
            comando = String.format("UPDATE ovos " +
                            "SET  data='%s', lote='%s', qualidade='%s', quantidade=%d, tipo_ave='%s', incubacao='%s'" +
                            " WHERE codigo=%d;",
                    ovos.getData(), ovos.getLote(), ovos.getQualidade(), ovos.getQuantidade(), ovos.getTipoAve(),
                    ovos.getIncubacao(), ovos.getCodigo());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
