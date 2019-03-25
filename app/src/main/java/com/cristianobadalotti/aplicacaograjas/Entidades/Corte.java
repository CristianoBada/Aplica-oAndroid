package com.cristianobadalotti.aplicacaograjas.Entidades;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Corte extends _Default implements Serializable {

    private int codigoCorte = -1;
    private int quantidadeAves;
    private int mortalidade;
    private String comentario;
    private int maximo;
    private String dataEntrada;
    private String dataSaida;
    private String tipoAve;

    public ArrayList<Corte> getLista() {
        BD bd = new BD();
        ArrayList<Corte> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM corte;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    Corte corte = new Corte();

                    corte.setCodigoCorte(resultSet.getInt("codigo_corte"));
                    corte.setComentario(resultSet.getString("comentario"));
                    corte.setDataEntrada(resultSet.getString("data_entrada"));
                    corte.setDataSaida(resultSet.getString("data_saida"));
                    corte.setMaximo(resultSet.getInt("maximo"));
                    corte.setMortalidade(resultSet.getInt("mortalidade"));
                    corte.setQuantidadeAves(resultSet.getInt("quantidade_aves"));
                    corte.setTipoAve(resultSet.getString("tipo_ave"));

                    lista.add(corte);
                    corte = null;
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return lista;
    }

    public void apagar() {
        String comando = String.format("DELETE FROM corte WHERE codigo_corte=%d;", this.getCodigoCorte());

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd._menssagem;
        this._status = bd._status;
    }

    public void salvar() {
        String comando = "";
        if (this.getCodigoCorte() == -1) {
            comando = String.format("INSERT INTO corte (comentario, data_entrada, data_saida, maximo, mortalidade, quantidade_aves, tipo_ave) " +
                            "VALUES ('%s', '%s', '%s', %d, %d, %d, '%s');",
                    this.getComentario(), this.getDataEntrada(), this.getDataSaida(), this.getMaximo(), this.getMortalidade(),
                    this.getQuantidadeAves(), this.getTipoAve());
        } else {
            comando = String.format("UPDATE corte " +
                            "SET  comentario='%s', data_entrada='%s', data_saida='%s', maximo=%d, mortalidade=%d, quantidade_aves=%d, tipo_ave='%s'" +
                            " WHERE codigo_corte=%d;",
                    this.getComentario(), this.getDataEntrada(), this.getDataSaida(), this.getMaximo(), this.getMortalidade(),
                    this.getQuantidadeAves(), this.getTipoAve(), this.getCodigoCorte());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd._menssagem;
        this._status = bd._status;
        bd.close();
    }

    public int getCodigoCorte() {
        return codigoCorte;
    }

    public void setCodigoCorte(int codigoCorte) {
        this.codigoCorte = codigoCorte;
    }

    public int getQuantidadeAves() {
        return quantidadeAves;
    }

    public void setQuantidadeAves(int quantidadeAves) {
        this.quantidadeAves = quantidadeAves;
    }

    public int getMortalidade() {
        return mortalidade;
    }

    public void setMortalidade(int mortalidade) {
        this.mortalidade = mortalidade;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getMaximo() {
        return maximo;
    }

    public void setMaximo(int maximo) {
        this.maximo = maximo;
    }

    public String getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(String dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    public String getDataSaida() {
        return dataSaida;
    }

    public void setDataSaida(String dataSaida) {
        this.dataSaida = dataSaida;
    }

    public String getTipoAve() {
        return tipoAve;
    }

    public void setTipoAve(String tipoAve) {
        this.tipoAve = tipoAve;
    }
}
