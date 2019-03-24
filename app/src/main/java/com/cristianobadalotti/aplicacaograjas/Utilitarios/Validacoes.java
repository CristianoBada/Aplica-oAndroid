package com.cristianobadalotti.aplicacaograjas.Utilitarios;

import android.text.TextUtils;
import android.util.Patterns;

public class Validacoes {

    public static boolean isCampoVazio(String valor) {
        boolean resultado = (TextUtils.isEmpty(valor) || valor.trim().isEmpty());
        return resultado;
    }

    public static boolean isEmailValido(String email) {
        boolean resultado = (!Validacoes.isCampoVazio(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
        return resultado;
    }

    public static boolean isInteiroPositivo(int num) {
        boolean resultado = (num >= 0);
        return resultado;
    }
}
