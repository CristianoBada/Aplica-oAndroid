package com.cristianobadalotti.aplicacaograjas.Entidades;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Postura extends _Default implements Serializable {
    private int codigoPostura;
    private int quantidade;
    private String comentario;
    private int maximoAves;
    private String dataentrada;
    private String datasaida;
    private String tipoAve;

    public Postura() {
        super();
        this.codigoPostura = -1;
        this.quantidade = 0;
        this.comentario = "";
        this.maximoAves = 0;
        this.dataentrada = "";
        this.datasaida = "";
        this.tipoAve = "";
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

    public void apagar() {
        String comando = String.format("DELETE FROM postura WHERE codigo_postura=%d;", this.getCodigoPostura());

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd._menssagem;
        this._status = bd._status;
    }

    public void salvar() {
        String comando = "";
        if (this.getCodigoPostura() == -1) {
            comando = String.format("INSERT INTO postura (comentario, dataentrada, datasaida, maximo_aves, quantidade, tipo_ave) VALUES ('%s', '%s', '%s', %d, %d, '%s');",
                    this.getComentario(), this.getDataentrada(), this.getDatasaida(), this.getMaximoAves(),
                    this.getQuantidade(), this.getTipoAve());
        } else {
            comando = String.format("UPDATE postura " +
                            "SET  comentario='%s', dataentrada='%s', datasaida='%s', maximo_aves=%d, quantidade='%d', tipo_ave='%s'" +
                            " WHERE codigo_postura=%d;",
                    this.getComentario(), this.getDataentrada(), this.getDatasaida(), this.getMaximoAves(),
                    this.getQuantidade(), this.getTipoAve(), this.getCodigoPostura());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd._menssagem;
        this._status = bd._status;
        bd.close();
    }

    public int getCodigoPostura() {
        return codigoPostura;
    }

    public void setCodigoPostura(int codigoPostura) {
        this.codigoPostura = codigoPostura;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getMaximoAves() {
        return maximoAves;
    }

    public void setMaximoAves(int maximoAves) {
        this.maximoAves = maximoAves;
    }

    public String getDataentrada() {
        return dataentrada;
    }

    public void setDataentrada(String dataentrada) {
        this.dataentrada = dataentrada;
    }

    public String getDatasaida() {
        return datasaida;
    }

    public void setDatasaida(String datasaida) {
        this.datasaida = datasaida;
    }

    public String getTipoAve() {
        return tipoAve;
    }

    public void setTipoAve(String tipoAve) {
        this.tipoAve = tipoAve;
    }
}
