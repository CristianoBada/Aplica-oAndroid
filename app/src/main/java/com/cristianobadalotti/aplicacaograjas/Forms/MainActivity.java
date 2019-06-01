package com.cristianobadalotti.aplicacaograjas.Forms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Validacoes;

public class MainActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        progressDialog = new ProgressDialog(this);
    }

    public void abreListaPostura(View view) {
        criaProgress();
        Intent intent = new Intent(MainActivity.this, PosturaListaActivity.class);
        startActivity(intent);
    }

    public void abreListaOvos(View view) {
        Intent intent = new Intent(MainActivity.this, OvosListaActivity.class);
        startActivity(intent);
    }

    public void abreListaCorte(View view) {
        criaProgress();
        Intent intent = new Intent(MainActivity.this, CorteActivity.class);
        startActivity(intent);
    }

    public void abreListaFinanceiro(View view) {
        criaProgress();
        Intent intent = new Intent(MainActivity.this, FinanceiroActivity.class);
        startActivity(intent);
    }

    public void abreListaRacoes(View view) {
        criaProgress();
        Intent intent = new Intent(MainActivity.this, RacoesActivity.class);
        startActivity(intent);
    }

    public void abreListaVacinas(View view) {
        criaProgress();
        Intent intent = new Intent(MainActivity.this, VacinasActivity.class);
        startActivity(intent);
    }

    public void abreListaIncubatorio(View view) {
        criaProgress();
        Intent intent = new Intent(MainActivity.this, IncubatorioActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        if (!hasFocus) {
            progressDialog.cancel();
        }
    }

    public void sairTela(View view) {
        finish();
    }

    private void criaProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("AGUARDE");
        progressDialog.setMessage("Carregando...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
    }
}
