package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Vacina;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Conversoes;

import java.sql.ResultSet;
import java.util.ArrayList;

public class VacinaBD extends _Default {
    public ArrayList<Vacina> getLista() {
        BD bd = new BD();
        ArrayList<Vacina> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM vacina;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    Vacina vacina = new Vacina();

                    vacina.setCodigoVacina(resultSet.getInt("codigo"));
                    vacina.setDataTratamento(resultSet.getString("data2"));
                    vacina.setDetalhe(resultSet.getString("observacao"));
                    vacina.setTipoTratamento(resultSet.getString("tipo"));
                    vacina.setCodigoCorte(resultSet.getInt("corte"));
                    vacina.setCodigoPostura(resultSet.getInt("postura"));

                    lista.add(vacina);
                    vacina = null;
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return lista;
    }

    public void apagar(int codigo) {
        String comando = String.format("DELETE FROM vacina WHERE codigo=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public boolean isUserPostura( int codigo) {
        BD bd = new BD();
        ArrayList<String> lista = new ArrayList<>();

        try {
            String comando = String.format("SELECT * FROM vacina WHERE postura=%d;", codigo);
            ResultSet resultSet = bd.select(comando);

            if (resultSet != null) {
                while (resultSet.next()) {
                    lista.add(resultSet.getInt("codigo") + "");
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }
        if (lista.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean isUserCorte( int codigo) {
        BD bd = new BD();
        ArrayList<String> lista = new ArrayList<>();

        try {
            String comando = String.format("SELECT * FROM vacina WHERE corte=%d;", codigo);
            ResultSet resultSet = bd.select(comando);

            if (resultSet != null) {
                while (resultSet.next()) {
                    lista.add(resultSet.getInt("codigo") + "");
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }
        if (lista.size() > 0) {
            return true;
        }
        return false;
    }

    public void salvar(Vacina vacina) {
        String comando = "";
        String dataC = new Conversoes().convertDateBRtoDataUS(vacina.getDataTratamento());
        if (vacina.getCodigoVacina() == -1) {
            comando = String.format("INSERT INTO vacina (data2, observacao, tipo, data, corte, postura) " +
                            "VALUES ('%s', '%s', '%s', '%s', %d, %d);",
                    vacina.getDataTratamento(), vacina.getDetalhe(), vacina.getTipoTratamento(),dataC,
                    vacina.getCodigoCorte(), vacina.getCodigoPostura());
        } else {
            comando = String.format("UPDATE vacina " +
                            "SET  data2='%s', observacao='%s', tipo='%s', data='%s', corte=%d, postura=%d" +
                            " WHERE codigo=%d;",
                    vacina.getDataTratamento(), vacina.getDetalhe(), vacina.getTipoTratamento(), dataC,
                    vacina.getCodigoCorte(), vacina.getCodigoPostura(), vacina.getCodigoVacina());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
