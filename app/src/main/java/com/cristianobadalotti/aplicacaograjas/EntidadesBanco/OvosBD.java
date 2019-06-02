package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Ovos;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Conversoes;

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
                    ovos.setData(resultSet.getString("data2"));
                    ovos.setQualidade(resultSet.getString("qualidade"));
                    ovos.setQuantidade(resultSet.getInt("quantidade"));
                    ovos.setTipoAve(resultSet.getString("tipoave"));
                    ovos.setPostura(resultSet.getInt("postura"));

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

    public ArrayList<String> getListaString() {
        BD bd = new BD();
        ArrayList<String> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM ovos;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    lista.add(resultSet.getInt("codigo") + " " + resultSet.getString("tipoave"));;
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
            comando = String.format("INSERT INTO ovos (data2, postura, qualidade, quantidade, tipoave, data) VALUES ('%s', %d, '%s', %d, '%s', '%s');",
                    ovos.getData(), ovos.getPostura(), ovos.getQualidade(), ovos.getQuantidade(), ovos.getTipoAve(),
                    new Conversoes().convertDateBRtoDataUS(ovos.getData()));
        } else {
            comando = String.format("UPDATE ovos " +
                            "SET  data='%s', postura=%d, qualidade='%s', quantidade=%d, tipoave='%s', data2='%s'" +
                            " WHERE codigo=%d;",
                    new Conversoes().convertDateBRtoDataUS(ovos.getData()), ovos.getPostura(), ovos.getQualidade(), ovos.getQuantidade(), ovos.getTipoAve(),
                    ovos.getData(), ovos.getCodigo());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
