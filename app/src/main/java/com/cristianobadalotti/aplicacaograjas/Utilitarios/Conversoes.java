package com.cristianobadalotti.aplicacaograjas.Utilitarios;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Conversoes {
    public String convertDateUStoDataBR(String dataUS) {
        try {
            DateFormat formatUS = new SimpleDateFormat("yyyy-mm-dd");
            Date date = (Date) formatUS.parse(dataUS);
            DateFormat formatBR = new SimpleDateFormat("dd/mm/yyyy");
            String dateBR = formatBR.format(date);
            return dateBR;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dataUS;
    }

    public String convertDateBRtoDataUS(String dataBR) {
        try {
            DateFormat formatUS = new SimpleDateFormat("dd/mm/yyyy");
            Date date = (Date) formatUS.parse(dataBR);
            DateFormat formatBR = new SimpleDateFormat("yyyy-mm-dd");
            String dateUS = formatBR.format(date);
            return dateUS;
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return dataBR;
    }
}
