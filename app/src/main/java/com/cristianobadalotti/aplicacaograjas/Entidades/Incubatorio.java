package com.cristianobadalotti.aplicacaograjas.Entidades;

import java.io.Serializable;

public class Incubatorio implements Serializable {

    private int codigoIncubatorio = -1;
    private String loteOvos;
    private Integer temperatura;
    private Integer umidade = 0;
    private Integer tempoChocar;
    private String dataInicio;
    private Integer mortalidade = 0;
    private String tipoAve;

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
