package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Vacina;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;

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
                    vacina.setDataTratamento(resultSet.getString("data"));
                    vacina.setDetalhe(resultSet.getString("observacao"));
                    vacina.setTipoTratamento(resultSet.getString("tipo"));

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

    public void salvar(Vacina vacina) {
        String comando = "";
        if (vacina.getCodigoVacina() == -1) {
            comando = String.format("INSERT INTO vacina (data, observacao, tipo) " +
                            "VALUES ('%s', '%s', '%s');",
                    vacina.getDataTratamento(), vacina.getDetalhe(), vacina.getTipoTratamento());
        } else {
            comando = String.format("UPDATE vacina " +
                            "SET  data='%s', observacao='%s', tipo='%s'" +
                            " WHERE codigo=%d;",
                    vacina.getDataTratamento(), vacina.getDetalhe(), vacina.getTipoTratamento(),
                    vacina.getCodigoVacina());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
