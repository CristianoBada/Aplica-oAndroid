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

import com.cristianobadalotti.aplicacaograjas.Adapters.OvosAdapter;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.OvosBD;
import com.cristianobadalotti.aplicacaograjas.R;

public class OvosListaActivity extends AppCompatActivity {

    private RecyclerView dadosOvos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ovos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dadosOvos = (RecyclerView)findViewById(R.id.dadosOvos);

        dadosOvos.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        dadosOvos.setLayoutManager(linearLayoutManager);

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
        OvosAdapter ovosAdapter = new OvosAdapter(new OvosBD().getLista());

        dadosOvos.setAdapter(ovosAdapter);
    }

    public void abreNovoOvos(View view) {
        Intent intent = new Intent(OvosListaActivity.this, EditarOvosActivity.class);
        startActivityForResult(intent, 0);
    }
}
