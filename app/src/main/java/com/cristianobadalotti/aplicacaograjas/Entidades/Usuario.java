package com.cristianobadalotti.aplicacaograjas.Entidades;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;

import java.io.Serializable;
import java.sql.ResultSet;

public class Usuario implements Serializable {
    private String nome;
    private String senha;

    public Usuario(String nome, String senha) {
        super();
        this.nome = nome;
        this.senha = senha;
    }

    public Usuario() {
        super();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
