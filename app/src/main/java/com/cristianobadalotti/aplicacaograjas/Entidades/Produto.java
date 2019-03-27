package com.cristianobadalotti.aplicacaograjas.Entidades;

import java.io.Serializable;

public class Produto  implements Serializable {
    private String nomeProduto;

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }
}
