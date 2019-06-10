package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Corte;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Conversoes;

import java.sql.ResultSet;
import java.util.ArrayList;

public class CorteBD  extends _Default {
    public ArrayList<Corte> getLista() {
        BD bd = new BD();
        ArrayList<Corte> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM corte;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    Corte corte = new Corte();

                    corte.setCodigoCorte(resultSet.getInt("codigo"));
                    corte.setComentario(resultSet.getString("observacao"));
                    corte.setDataEntrada(resultSet.getString("entrada2"));
                    corte.setDataSaida(resultSet.getString("saida2"));
                    corte.setMaximo(resultSet.getInt("maximo"));
                    corte.setMortalidade(resultSet.getInt("mortalidade"));
                    corte.setQuantidadeAves(resultSet.getInt("quantidade"));
                    corte.setTipoAve(resultSet.getString("tipoave"));

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

    public ArrayList<String> getListaString() {
        BD bd = new BD();
        ArrayList<String> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM corte;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    lista.add(resultSet.getInt("codigo") + "");
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return lista;
    }

    public void apagar(int codigo) {
        String comando = String.format("DELETE FROM corte WHERE codigo=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public void salvar(Corte corte) {
        String comando = "";
        if (corte.getCodigoCorte() == -1) {
            comando = String.format("INSERT INTO corte (observacao, entrada2, saida2, maximo, mortalidade, quantidade, tipoave, entrada, saida) " +
                            "VALUES ('%s', '%s', '%s', %d, %d, %d, '%s', '%s', '%s');",
                    corte.getComentario(), corte.getDataEntrada(), corte.getDataSaida(), corte.getMaximo(), corte.getMortalidade(),
                    corte.getQuantidadeAves(), corte.getTipoAve(), new Conversoes().convertDateBRtoDataUS(corte.getDataEntrada()),
                    new Conversoes().convertDateBRtoDataUS(corte.getDataSaida()));
        } else {
            comando = String.format("UPDATE corte " +
                            "SET  observacao='%s', entrada2='%s', saida2='%s', maximo=%d, mortalidade=%d, quantidade=%d, tipoave='%s', entrada='%s', saida='%s'" +
                            " WHERE codigo=%d;",
                    corte.getComentario(), corte.getDataEntrada(), corte.getDataSaida(), corte.getMaximo(), corte.getMortalidade(),
                    corte.getQuantidadeAves(), corte.getTipoAve(), new Conversoes().convertDateBRtoDataUS(corte.getDataEntrada()),
                    new Conversoes().convertDateBRtoDataUS(corte.getDataSaida()), corte.getCodigoCorte());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
