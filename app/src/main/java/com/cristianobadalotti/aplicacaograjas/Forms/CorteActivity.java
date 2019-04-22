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

import com.cristianobadalotti.aplicacaograjas.Adapters.CorteAdapter;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.CorteBD;
import com.cristianobadalotti.aplicacaograjas.R;

public class CorteActivity extends AppCompatActivity {

    private RecyclerView dadosCorte;
    public ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corte);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Lista de Granjas de Corte");

        dadosCorte = (RecyclerView)findViewById(R.id.dadosCorte);

        dadosCorte.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        dadosCorte.setLayoutManager(linearLayoutManager);

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
        CorteAdapter corteAdapter = new CorteAdapter(new CorteBD().getLista());

        dadosCorte.setAdapter(corteAdapter);
    }

    private void criaProgressDialog() {
        progressDialog = new ProgressDialog(CorteActivity.this);
        progressDialog.setMessage("Carregando... Espere um momento...");
        progressDialog.setIndeterminate(true);
        progressDialog.show();
    }

    public void abreNovoCorte(View view) {

        Intent intent = new Intent(CorteActivity.this, SplashActivity.class);
        intent.putExtra("CODE", 1);
        startActivityForResult(intent, 0);
    }
}
