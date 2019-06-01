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

import com.cristianobadalotti.aplicacaograjas.Adapters.PosturaAdapter;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.PosturaBD;
import com.cristianobadalotti.aplicacaograjas.R;

public class PosturaListaActivity extends AppCompatActivity {

    private RecyclerView dadosPostura;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postura_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lista de Granjas Postura");

        dadosPostura = (RecyclerView)findViewById(R.id.dadosPostura);

        dadosPostura.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        dadosPostura.setLayoutManager(linearLayoutManager);

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

        PosturaAdapter posturaAdapter = new PosturaAdapter(new PosturaBD().getLista());

        dadosPostura.setAdapter(posturaAdapter);
    }

    public void abreNovoPostura(View view) {
        criaProgress();
        Intent intent = new Intent(PosturaListaActivity.this, EditarPosturaActivity.class);
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
