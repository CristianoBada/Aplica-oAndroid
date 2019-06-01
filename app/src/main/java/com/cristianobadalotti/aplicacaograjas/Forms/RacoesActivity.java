package com.cristianobadalotti.aplicacaograjas.Forms;

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
        RacaoAdapter racaoAdapter = new RacaoAdapter(new RacaoBD().getLista());

        dadosRacao.setAdapter(racaoAdapter);
    }

    public void abreNovoRacao(View view) {
        Intent intent = new Intent(RacoesActivity.this, EditarRacoesActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onBackPressed() {
    }
}
