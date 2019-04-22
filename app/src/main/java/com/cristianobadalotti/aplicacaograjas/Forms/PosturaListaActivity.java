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

import com.cristianobadalotti.aplicacaograjas.Adapters.PosturaAdapter;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.PosturaBD;
import com.cristianobadalotti.aplicacaograjas.R;

public class PosturaListaActivity extends AppCompatActivity {

    private RecyclerView dadosPostura;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postura_lista);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        PosturaAdapter posturaAdapter = new PosturaAdapter(new PosturaBD().getLista());

        dadosPostura.setAdapter(posturaAdapter);
    }

    public void abreNovoPostura(View view) {
        Intent intent = new Intent(PosturaListaActivity.this, EditarPosturaActivity.class);
        startActivityForResult(intent, 0);
    }
}
