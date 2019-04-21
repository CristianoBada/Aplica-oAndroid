package com.cristianobadalotti.aplicacaograjas.EntidadesBanco;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;
import com.cristianobadalotti.aplicacaograjas.Entidades.Incubatorio;
import com.cristianobadalotti.aplicacaograjas.Entidades._Default;

import java.sql.ResultSet;
import java.util.ArrayList;

public class IncubatorioBD  extends _Default {

    public ArrayList<Incubatorio> getLista() {
        BD bd = new BD();
        ArrayList<Incubatorio> lista = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM incubatorio;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    Incubatorio incubatorio = new Incubatorio();

                    incubatorio.setCodigoIncubatorio(resultSet.getInt("codigo"));
                    incubatorio.setDataInicio(resultSet.getString("inicio"));
                    incubatorio.setLoteOvos(resultSet.getString("loteovos"));
                    incubatorio.setMortalidade(resultSet.getInt("mortalidade"));
                    incubatorio.setTemperatura(resultSet.getInt("temperatura"));
                    incubatorio.setTempoChocar(resultSet.getInt("tempo"));
                    incubatorio.setTipoAve(resultSet.getString("tipoave"));
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

    public void apagar(int codigo) {
        String comando = String.format("DELETE FROM incubatorio WHERE codigo=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public void salvar(Incubatorio incubatorio) {
        String comando = "";
        if (incubatorio.getCodigoIncubatorio() == -1) {
            comando = String.format("INSERT INTO incubatorio (inicio, loteovos, mortalidade, temperatura, tempo, tipoave, umidade) " +
                            "VALUES ('%s', '%s', %d, %d, %d, '%s', %d);",
                    incubatorio.getDataInicio(), incubatorio.getLoteOvos(), incubatorio.getMortalidade(), incubatorio.getTemperatura(), incubatorio.getTempoChocar(),
                    incubatorio.getTipoAve(), incubatorio.getUmidade());
        } else {
            comando = String.format("UPDATE incubatorio " +
                            "SET  inicio='%s', loteovos='%s', mortalidade=%d, temperatura=%d, tempo=%d, tipoave='%s', umidade=%d" +
                            " WHERE codigo=%d;",
                    incubatorio.getDataInicio(), incubatorio.getLoteOvos(), incubatorio.getMortalidade(), incubatorio.getTemperatura(), incubatorio.getTempoChocar(),
                    incubatorio.getTipoAve(), incubatorio.getUmidade(), incubatorio.getCodigoIncubatorio());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
        bd.close();
    }
}
