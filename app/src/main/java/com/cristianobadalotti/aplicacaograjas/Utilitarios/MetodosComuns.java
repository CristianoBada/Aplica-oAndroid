package com.cristianobadalotti.aplicacaograjas.Utilitarios;

import java.util.ArrayList;

public class MetodosComuns {
    public static int achaPosicao(ArrayList<String> lista, String busca) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).contains(busca)) {
                return i;
            }
        }
        return -1;
    }
}
