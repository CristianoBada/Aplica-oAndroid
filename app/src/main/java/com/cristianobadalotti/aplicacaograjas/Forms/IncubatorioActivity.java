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

import com.cristianobadalotti.aplicacaograjas.Adapters.IncubatorioAdapter;
import com.cristianobadalotti.aplicacaograjas.Entidades.Incubatorio;
import com.cristianobadalotti.aplicacaograjas.R;

public class IncubatorioActivity extends AppCompatActivity {

    private RecyclerView dadosIncubatorio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incubatorio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dadosIncubatorio = (RecyclerView)findViewById(R.id.dadosIncubatorio);

        dadosIncubatorio.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        dadosIncubatorio.setLayoutManager(linearLayoutManager);

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
        dadosIncubatorio.setAdapter(new IncubatorioAdapter(new Incubatorio().getLista()));
    }

    public void abreNovoIncubatorio(View view) {
        Intent intent = new Intent(IncubatorioActivity.this, EditarIncubatorioActivity.class);
        startActivityForResult(intent, 0);
    }
}
