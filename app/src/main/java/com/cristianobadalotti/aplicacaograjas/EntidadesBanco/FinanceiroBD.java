package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Financeiro;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;

import java.sql.ResultSet;
import java.util.ArrayList;

public class FinanceiroBD extends _Default {
    public ArrayList<Financeiro> getLista() {
        BD bd = new BD();
        ArrayList<Financeiro> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM financeiro;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    Financeiro financeiro = new Financeiro();

                    financeiro.setEntrasaida(resultSet.getString("entrasaida"));
                    financeiro.setCodigoFinanceiro(resultSet.getInt("codigo_financeiro"));
                    financeiro.setDetalhe(resultSet.getString("detalhe"));
                    financeiro.setNome(resultSet.getString("nome"));
                    financeiro.setValor(resultSet.getDouble("valor"));

                    lista.add(financeiro);
                    financeiro = null;
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return lista;
    }

    public void apagar(int codigo) {
        String comando = String.format("DELETE FROM financeiro WHERE codigo_financeiro=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public void salvar(Financeiro financeiro) {
        String comando = "";
        if (financeiro.getCodigoFinanceiro() == -1) {
            comando = String.format("INSERT INTO financeiro (entrasaida, detalhe, nome, valor) " +
                            "VALUES ('%s', '%s', '%s', %f);",
                    financeiro.getEntrasaida(), financeiro.getDetalhe(), financeiro.getNome(), financeiro.getValor());
        } else {
            comando = String.format("UPDATE financeiro " +
                            "SET  entrasaida='%s', detalhe='%s', nome='%s', valor=%f" +
                            " WHERE codigo_financeiro=%d;",
                    financeiro.getEntrasaida(), financeiro.getDetalhe(), financeiro.getNome(), financeiro.getValor(),
                    financeiro.getCodigoFinanceiro());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
