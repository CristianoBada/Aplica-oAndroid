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

import com.cristianobadalotti.aplicacaograjas.Adapters.VacinaAdapter;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.VacinaBD;
import com.cristianobadalotti.aplicacaograjas.R;

public class VacinasActivity extends AppCompatActivity {

    private RecyclerView dadosVacina;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vacinas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dadosVacina = (RecyclerView)findViewById(R.id.dadosVacina);

        dadosVacina.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        dadosVacina.setLayoutManager(linearLayoutManager);

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
        dadosVacina.setAdapter(new VacinaAdapter(new VacinaBD().getLista()));
    }

    public void abreNovoVacina(View view) {
        Intent intent = new Intent(VacinasActivity.this, EditarVacinasActivity.class);
        startActivityForResult(intent, 0);
    }
}
