package com.cristianobadalotti.aplicacaograjas.Banco;

import android.app.AlertDialog;
import android.os.AsyncTask;

import java.sql.Connection;
import java.sql.ResultSet;

public class ExecuteBD extends AsyncTask<String, Void, ResultSet> {

    private Connection connection;
    private String query;

    public ExecuteBD(Connection connection, String query) {
        this.connection = connection;
        this.query = query;
    }

    @Override
    protected ResultSet doInBackground(String... strings) {
        ResultSet resultSet = null;

        try {
            resultSet = connection.prepareCall(query).executeQuery();
        } catch (Exception e) {

        } finally {
            try {
                this.connection.close();
            } catch (Exception ex) {

            }
        }

        return resultSet;
    }
}
