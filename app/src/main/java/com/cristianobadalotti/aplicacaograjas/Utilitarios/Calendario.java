package com.cristianobadalotti.aplicacaograjas.Utilitarios;

import java.util.Calendar;

public class Calendario {
    private int mYearIni;
    private int mMonthIni;
    private int mDayIni;
    private int sYearIni;
    private int sMonthIni;
    private int sDayIni;

    public Calendario() {
        Calendar C = Calendar.getInstance();

        sMonthIni = C.get(Calendar.MONTH);
        sDayIni = C.get(Calendar.DAY_OF_MONTH);
        sYearIni = C.get(Calendar.YEAR);
    }

    public String getText() {
        return mDayIni + "/" +(mMonthIni + 1) + "/" +  mYearIni + " ";
    }

    public void setmYearIni(int mYearIni) {
        this.mYearIni = mYearIni;
    }

    public void setmMonthIni(int mMonthIni) {
        this.mMonthIni = mMonthIni;
    }

    public void setmDayIni(int mDayIni) {
        this.mDayIni = mDayIni;
    }

    public int getsYearIni() {
        return sYearIni;
    }
    public int getsMonthIni() {
        return sMonthIni;
    }

    public int getsDayIni() {
        return sDayIni;
    }
}
