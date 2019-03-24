package com.cristianobadalotti.aplicacaograjas.Forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Validacoes;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    public void abreNovoCliente(View view) {
        Intent intent = new Intent(MainActivity.this, ActCadCliente.class);
        startActivityForResult(intent, 0);
    }

    public void abreListaClientes(View view) {
        Intent intent = new Intent(MainActivity.this, ActListaCliente.class);
        startActivity(intent);
    }

    public void abreListaPostura(View view) {
        Intent intent = new Intent(MainActivity.this, PosturaListaActivity.class);
        startActivity(intent);
    }

    public void abreListaOvos(View view) {
        Intent intent = new Intent(MainActivity.this, OvosListaActivity.class);
        startActivity(intent);
    }

    public void abreListaCorte(View view) {
        Intent intent = new Intent(MainActivity.this, CorteActivity.class);
        startActivity(intent);
    }

    public void abreListaFinanceiro(View view) {
        Intent intent = new Intent(MainActivity.this, FinanceiroActivity.class);
        startActivity(intent);
    }

    public void abreListaRacoes(View view) {
        Intent intent = new Intent(MainActivity.this, RacoesActivity.class);
        startActivity(intent);
    }

    public void abreListaVacinas(View view) {
        Intent intent = new Intent(MainActivity.this, VacinasActivity.class);
        startActivity(intent);
    }

    public void abreListaIncubatorio(View view) {
        Intent intent = new Intent(MainActivity.this, IncubatorioActivity.class);
        startActivity(intent);
    }
}
