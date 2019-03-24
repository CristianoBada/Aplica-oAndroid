package com.cristianobadalotti.aplicacaograjas.Entidades;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Ovos extends _Default implements Serializable {
    private int codigo;
    private int quantidade;
    private String qualidade;
    private String data;
    private String incubacao;
    private String lote;
    private String tipoAve;

    public Ovos() {
        super();
        this.codigo = -1;
        this.data = "";
        this.qualidade = "";
        this.incubacao = "F";
        this.lote = "";
        this.quantidade = 0;
        this.tipoAve = "";
    }

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

    public void apagar() {
        String comando = String.format("DELETE FROM ovos WHERE codigo=%d;", this.getCodigo());

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd._menssagem;
        this._status = bd._status;
    }

    public void salvar() {
        String comando = "";
        if (this.getCodigo() == -1) {
            comando = String.format("INSERT INTO ovos (data, lote, qualidade, quantidade, tipo_ave, incubacao) VALUES ('%s', '%s', '%s', %d, '%s', '%s');",
                    this.getData(), this.getLote(), this.getQualidade(), this.getQuantidade(), this.getTipoAve(),
                    this.getIncubacao());
        } else {
            comando = String.format("UPDATE ovos " +
                            "SET  data='%s', lote='%s', qualidade='%s', quantidade=%d, tipo_ave='%s', incubacao='%s'" +
                            " WHERE codigo=%d;",
                    this.getData(), this.getLote(), this.getQualidade(), this.getQuantidade(), this.getTipoAve(),
                    this.getIncubacao(), this.getCodigo());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd._menssagem;
        this._status = bd._status;
        bd.close();
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIncubacao() {
        return incubacao;
    }

    public void setIncubacao(String incubacao) {
        this.incubacao = incubacao;
    }

    public String getLote() {
        return lote;
    }

    public void setLote(String lote) {
        this.lote = lote;
    }

    public String getTipoAve() {
        return tipoAve;
    }

    public void setTipoAve(String tipoAve) {
        this.tipoAve = tipoAve;
    }
}
