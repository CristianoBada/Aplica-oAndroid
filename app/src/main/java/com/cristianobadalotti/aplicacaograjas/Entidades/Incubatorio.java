package com.cristianobadalotti.aplicacaograjas.Entidades;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Incubatorio extends _Default implements Serializable {

    private int codigoIncubatorio = -1;
    private String loteOvos;
    private Integer temperatura;
    private Integer umidade;
    private Integer tempoChocar;
    private String dataInicio;
    private Integer mortalidade;
    private String tipoAve;

    public ArrayList<Incubatorio> getLista() {
        BD bd = new BD();
        ArrayList<Incubatorio> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM incubatorio;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    Incubatorio incubatorio = new Incubatorio();

                    incubatorio.setCodigoIncubatorio(resultSet.getInt("codigo_incubatorio"));
                    incubatorio.setDataInicio(resultSet.getString("data_inicio"));
                    incubatorio.setLoteOvos(resultSet.getString("lote_ovos"));
                    incubatorio.setMortalidade(resultSet.getInt("mortalidade"));
                    incubatorio.setTemperatura(resultSet.getInt("temperatura"));
                    incubatorio.setTempoChocar(resultSet.getInt("tempo_chocar"));
                    incubatorio.setTipoAve(resultSet.getString("tipo_ave"));
                    incubatorio.setUmidade(resultSet.getInt("umidade"));

                    lista.add(incubatorio);
                    incubatorio = null;
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return lista;
    }

    public void apagar() {
        String comando = String.format("DELETE FROM incubatorio WHERE codigo_incubatorio=%d;", this.getCodigoIncubatorio());

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd._menssagem;
        this._status = bd._status;
    }

    public void salvar() {
        String comando = "";
        if (this.getCodigoIncubatorio() == -1) {
            comando = String.format("INSERT INTO incubatorio (data_inicio, lote_ovos, mortalidade, temperatura, tempo_chocar, tipo_ave, umidade) " +
                            "VALUES ('%s', '%s', %d, %d, %d, '%s', %d);",
                    this.getDataInicio(), this.getLoteOvos(), this.getMortalidade(), this.getTemperatura(), this.getTempoChocar(),
                    this.getTipoAve(), this.getUmidade());
        } else {
            comando = String.format("UPDATE incubatorio " +
                            "SET  data_inicio='%s', lote_ovos='%s', mortalidade=%d, temperatura=%d, tempo_chocar=%d, tipo_ave='%s', umidade=%d" +
                            " WHERE codigo_incubatorio=%d;",
                    this.getDataInicio(), this.getLoteOvos(), this.getMortalidade(), this.getTemperatura(), this.getTempoChocar(),
                    this.getTipoAve(), this.getUmidade(), this.getCodigoIncubatorio());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd._menssagem;
        this._status = bd._status;
        bd.close();
    }

    public int getCodigoIncubatorio() {
        return codigoIncubatorio;
    }

    public void setCodigoIncubatorio(int codigoIncubatorio) {
        this.codigoIncubatorio = codigoIncubatorio;
    }

    public String getLoteOvos() {
        return loteOvos;
    }

    public void setLoteOvos(String loteOvos) {
        this.loteOvos = loteOvos;
    }

    public Integer getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(Integer temperatura) {
        this.temperatura = temperatura;
    }

    public Integer getUmidade() {
        return umidade;
    }

    public void setUmidade(Integer umidade) {
        this.umidade = umidade;
    }

    public Integer getTempoChocar() {
        return tempoChocar;
    }

    public void setTempoChocar(Integer tempoChocar) {
        this.tempoChocar = tempoChocar;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Integer getMortalidade() {
        return mortalidade;
    }

    public void setMortalidade(Integer mortalidade) {
        this.mortalidade = mortalidade;
    }

    public String getTipoAve() {
        return tipoAve;
    }

    public void setTipoAve(String tipoAve) {
        this.tipoAve = tipoAve;
    }
}
