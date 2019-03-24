package com.cristianobadalotti.aplicacaograjas.Entidades;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;

import java.io.Serializable;
import java.sql.ResultSet;

public class Usuario extends _Default implements Serializable {
    private String nome;
    private String senha;

    public Usuario(String nome, String senha) {
        super();
        this.nome = nome;
        this.senha = senha;
    }

    public boolean validaUsuario() {
        BD bd = new BD();
        boolean valido = false;

        try {
            String comando = String.format("SELECT * FROM usuario WHERE login='%s' AND senha='%s';",
                    this.getNome(), this.getSenha());
            ResultSet resultSet = bd.select(comando);
            if (resultSet != null) {
                valido = true;
            }
        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        this._menssagem = bd._menssagem;
        this._status = bd._status;
        return valido;
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
