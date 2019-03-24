package com.cristianobadalotti.aplicacaograjas.Banco;

import com.cristianobadalotti.aplicacaograjas.Entidades._Default;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class BD extends _Default implements Runnable{

    private Connection conn;
    private String host = "ec2-54-204-14-96.compute-1.amazonaws.com";
    private String db = "d5jf4c06p6m2pv";
    private int port = 5432;
    private String user = "vpmngamaiuvqjh";
    private String pass = "8c0e380c769d7cfa800702c00a7cf3c2ea52f58c4d17620ef5352c951615152a";
    private String url = "jdbc:postgresql://%s:%d/%s";

    public BD() {
        super();
        this.url = String.format(this.url, this.host, this.port, this.db);
    }

    @Override
    public void run() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection(this.url, this.user, this.pass);
        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }
    }

    private void conecta(){
        Thread thread = new Thread(this);
        thread.start();
        try {
            thread.join();
        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }
    }

    private void disconecta() {
        if (this.conn != null) {
            try {
                this.conn.close();
            } catch (Exception e) {

            } finally {
                this.conn = null;
            }
        }
    }

    public void close() {
        this.disconecta();
    }

    public ResultSet select(String query) {
        this.conecta();
        ResultSet resultSet = null;

        try {
            resultSet = new ExecuteBD(this.conn, query).execute().get();
        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return resultSet;
    }

    public ResultSet execute(String query) {
        this.conecta();
        ResultSet resultSet = null;

        try {
            resultSet = new ExecuteBD(this.conn, query).execute().get();
        } catch (Exception e) {
            this._menssagem = e.getMessage();
            this._status = false;
        }

        return resultSet;
    }
}
