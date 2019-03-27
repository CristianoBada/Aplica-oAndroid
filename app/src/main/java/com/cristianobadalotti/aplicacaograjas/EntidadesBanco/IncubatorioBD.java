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

    public void apagar(int codigo) {
        String comando = String.format("DELETE FROM incubatorio WHERE codigo_incubatorio=%d;", codigo);

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd.get_menssagem();
        this._status = bd.get_status();
    }

    public void salvar(Incubatorio incubatorio) {
        String comando = "";
        if (incubatorio.getCodigoIncubatorio() == -1) {
            comando = String.format("INSERT INTO incubatorio (data_inicio, lote_ovos, mortalidade, temperatura, tempo_chocar, tipo_ave, umidade) " +
                            "VALUES ('%s', '%s', %d, %d, %d, '%s', %d);",
                    incubatorio.getDataInicio(), incubatorio.getLoteOvos(), incubatorio.getMortalidade(), incubatorio.getTemperatura(), incubatorio.getTempoChocar(),
                    incubatorio.getTipoAve(), incubatorio.getUmidade());
        } else {
            comando = String.format("UPDATE incubatorio " +
                            "SET  data_inicio='%s', lote_ovos='%s', mortalidade=%d, temperatura=%d, tempo_chocar=%d, tipo_ave='%s', umidade=%d" +
                            " WHERE codigo_incubatorio=%d;",
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
