package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Financeiro;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Conversoes;

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
                    financeiro.setCodigoFinanceiro(resultSet.getInt("codigo"));
                    financeiro.setDetalhe(resultSet.getString("observacao"));
                    financeiro.setNome(resultSet.getString("nome"));
                    financeiro.setValor(resultSet.getDouble("valor"));
                    financeiro.setData(resultSet.getString("data2"));

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
        String comando = String.format("DELETE FROM financeiro WHERE codigo=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public void salvar(Financeiro financeiro) {
        String comando = "";
        if (financeiro.getCodigoFinanceiro() == -1) {
            comando = String.format("INSERT INTO financeiro (entrasaida, observacao, nome, valor, data2, data) " +
                            "VALUES ('%s', '%s', '%s', %f, '%s', '%s');",
                    financeiro.getEntrasaida(), financeiro.getDetalhe(), financeiro.getNome(), financeiro.getValor(),
                    financeiro.getData(), new Conversoes().convertDateBRtoDataUS(financeiro.getData()));
        } else {
            comando = String.format("UPDATE financeiro " +
                            "SET  entrasaida='%s', observacao='%s', nome='%s', valor=%f, data2='%s', data='%s'" +
                            " WHERE codigo=%d;",
                    financeiro.getEntrasaida(), financeiro.getDetalhe(), financeiro.getNome(), financeiro.getValor(),
                    financeiro.getData(), new Conversoes().convertDateBRtoDataUS(financeiro.getData()), financeiro.getCodigoFinanceiro());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
