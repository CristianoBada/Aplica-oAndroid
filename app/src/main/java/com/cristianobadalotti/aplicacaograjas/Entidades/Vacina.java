package com.cristianobadalotti.aplicacaograjas.Entidades;

import java.io.Serializable;

public class Vacina implements Serializable {
    private int codigoVacina = -1;
    private String dataTratamento;
    private String tipoTratamento;
    private String detalhe;
    private int codigoPostura = 0;
    private int codigoCorte = 0;

    public int getCodigoVacina() {
        return codigoVacina;
    }

    public void setCodigoVacina(int codigoVacina) {
        this.codigoVacina = codigoVacina;
    }

    public String getDataTratamento() {
        return dataTratamento;
    }

    public void setDataTratamento(String dataTratamento) {
        this.dataTratamento = dataTratamento;
    }

    public String getTipoTratamento() {
        return tipoTratamento;
    }

    public void setTipoTratamento(String tipoTratamento) {
        this.tipoTratamento = tipoTratamento;
    }

    public String getDetalhe() {
        return detalhe;
    }

    public void setDetalhe(String detalhe) {
        this.detalhe = detalhe;
    }

    public int getCodigoPostura() {
        return codigoPostura;
    }

    public void setCodigoPostura(int codigoPostura) {
        this.codigoPostura = codigoPostura;
    }

    public int getCodigoCorte() {
        return codigoCorte;
    }

    public void setCodigoCorte(int codigoCorte) {
        this.codigoCorte = codigoCorte;
    }
}
