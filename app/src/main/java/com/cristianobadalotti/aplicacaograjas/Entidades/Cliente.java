package com.cristianobadalotti.aplicacaograjas.Entidades;

import com.cristianobadalotti.aplicacaograjas.Banco.BD;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Cliente extends _Default implements Serializable {

    private Integer id;
    private String nome;
    private String endereco;
    private String email;
    private String telefone;

    public Cliente() {
        super();
        this.id = -1;
        this.email = "";
        this.endereco = "";
        this.nome = "";
        this.telefone = "";
    }

    public ArrayList<Cliente> getLista() {
        BD bd = new BD();
        ArrayList<Cliente> listCliente = new ArrayList<>();

        try {
            ResultSet resultSet = bd.select("SELECT * FROM cliente;");

            if (resultSet != null) {
                while (resultSet.next()) {
                    Cliente cliente = new Cliente();
                    cliente.setId(resultSet.getInt("id"));
                    cliente.setNome(resultSet.getString("nome"));
                    cliente.setEndereco(resultSet.getString("endereco"));
                    cliente.setEmail(resultSet.getString("email"));
                    cliente.setTelefone(resultSet.getString("telefone"));

                    listCliente.add(cliente);
                    cliente = null;
                }
            }

        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return listCliente;
    }

    public void salvar() {
        String comando = "";
        if (this.getId() == -1) {
            comando = String.format("INSERT INTO cliente (nome, endereco, email, telefone) VALUES ('%s', '%s', '%s', '%s');",
                    this.getNome(), this.getEndereco(), this.getEmail(), this.getTelefone());
        } else {
            comando = String.format("UPDATE cliente SET nome='%s', endereco='%s', email='%s', telefone='%s' WHERE id=%d;",
                    this.getNome(), this.getEndereco(), this.getEmail(), this.getTelefone(), this.getId());
        }

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd._menssagem;
        this._status = bd._status;
    }

    public void apagar() {
        String comando = String.format("DELETE FROM cliente WHERE id=%d;", this.getId());

        BD bd = new BD();
        bd.execute(comando);
        this._menssagem = bd._menssagem;
        this._status = bd._status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
}
