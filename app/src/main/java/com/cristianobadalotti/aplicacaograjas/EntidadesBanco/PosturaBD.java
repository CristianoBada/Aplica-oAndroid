package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Postura;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Conversoes;

import java.sql.ResultSet;
import java.util.ArrayList;

public class PosturaBD extends _Default {
    public PosturaBD() {
        super();
    }

    public ArrayList<Postura> getLista() {
        BD bd = new BD();
        ArrayList<Postura> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM postura;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    Postura postura = new Postura();

                    postura.setCodigoPostura(resultSet.getInt("codigo"));
                    postura.setComentario(resultSet.getString("observacao"));
                    postura.setDataentrada(resultSet.getString("entrada2"));
                    postura.setDatasaida(resultSet.getString("saida2"));
                    postura.setMaximoAves(resultSet.getInt("maximo"));
                    postura.setQuantidade(resultSet.getInt("quantidade"));
                    postura.setTipoAve(resultSet.getString("tipoave"));

                    lista.add(postura);
                    postura = null;
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return lista;
    }

    public ArrayList<String> getListaString(boolean mostraTA) {
        BD bd = new BD();
        ArrayList<String> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM postura;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    if (mostraTA) {
                        lista.add(resultSet.getInt("codigo") + " " + resultSet.getString("tipoave"));
                    } else {
                        lista.add(resultSet.getInt("codigo") + "");
                    }
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return lista;
    }

    public void apagar(int codigo) {
        String comando = String.format("DELETE FROM postura WHERE codigo=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public void salvar(Postura postura) {
        String comando = "";
        if (postura.getCodigoPostura() == -1) {
            comando = String.format("INSERT INTO postura (observacao, entrada2, saida2, maximo, quantidade, tipoave, entrada, saida) VALUES ('%s', '%s', '%s', %d, %d, '%s', '%s', '%s');",
                    postura.getComentario(), postura.getDataentrada(), postura.getDatasaida(), postura.getMaximoAves(),
                    postura.getQuantidade(), postura.getTipoAve(),
                    new Conversoes().convertDateBRtoDataUS(postura.getDataentrada()), new Conversoes().convertDateBRtoDataUS(postura.getDatasaida()));
        } else {
            comando = String.format("UPDATE postura " +
                            "SET  observacao='%s', entrada2='%s', saida2='%s', maximo=%d, quantidade='%d', tipoave='%s', entrada='%s', saida='%s'" +
                            " WHERE codigo=%d;",
                    postura.getComentario(), postura.getDataentrada(), postura.getDatasaida(), postura.getMaximoAves(),
                    postura.getQuantidade(), postura.getTipoAve(), new Conversoes().convertDateBRtoDataUS(postura.getDataentrada()),
                    new Conversoes().convertDateBRtoDataUS(postura.getDatasaida()),
                    postura.getCodigoPostura());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
