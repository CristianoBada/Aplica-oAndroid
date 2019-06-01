package com.cristianobadalotti.aplicacaograjas.Forms;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cristianobadalotti.aplicacaograjas.Adapters.RacaoAdapter;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.RacaoBD;
import com.cristianobadalotti.aplicacaograjas.R;

public class RacoesActivity extends AppCompatActivity {

    private RecyclerView dadosRacao;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_racoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lista de Lotes de Rações");

        dadosRacao = (RecyclerView)findViewById(R.id.dadosRacao);

        dadosRacao.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        dadosRacao.setLayoutManager(linearLayoutManager);

        CreateAdapter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        CreateAdapter();
    }

    private void CreateAdapter() {
        if (progressDialog != null) {
            progressDialog.cancel();
        }
        dadosRacao.setAdapter(new RacaoAdapter(new RacaoBD().getLista()));
    }

    public void abreNovoRacao(View view) {
        criaProgress();
        Intent intent = new Intent(RacoesActivity.this, EditarRacoesActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onBackPressed() {
        criaProgress();
        finish();
    }

    public void voltarMenu(View view) {
        criaProgress();
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
