package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Postura;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;

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

                    postura.setCodigoPostura(resultSet.getInt("codigo_postura"));
                    postura.setComentario(resultSet.getString("comentario"));
                    postura.setDataentrada(resultSet.getString("dataentrada"));
                    postura.setDatasaida(resultSet.getString("datasaida"));
                    postura.setMaximoAves(resultSet.getInt("maximo_aves"));
                    postura.setQuantidade(resultSet.getInt("quantidade"));
                    postura.setTipoAve(resultSet.getString("tipo_ave"));

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

    public void apagar(int codigo) {
        String comando = String.format("DELETE FROM postura WHERE codigo_postura=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public void salvar(Postura postura) {
        String comando = "";
        if (postura.getCodigoPostura() == -1) {
            comando = String.format("INSERT INTO postura (comentario, dataentrada, datasaida, maximo_aves, quantidade, tipo_ave) VALUES ('%s', '%s', '%s', %d, %d, '%s');",
                    postura.getComentario(), postura.getDataentrada(), postura.getDatasaida(), postura.getMaximoAves(),
                    postura.getQuantidade(), postura.getTipoAve());
        } else {
            comando = String.format("UPDATE postura " +
                            "SET  comentario='%s', dataentrada='%s', datasaida='%s', maximo_aves=%d, quantidade='%d', tipo_ave='%s'" +
                            " WHERE codigo_postura=%d;",
                    postura.getComentario(), postura.getDataentrada(), postura.getDatasaida(), postura.getMaximoAves(),
                    postura.getQuantidade(), postura.getTipoAve(), postura.getCodigoPostura());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
