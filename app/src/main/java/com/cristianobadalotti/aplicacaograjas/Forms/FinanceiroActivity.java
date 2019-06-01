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

import com.cristianobadalotti.aplicacaograjas.Adapters.FinanceiroAdapter;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.FinanceiroBD;
import com.cristianobadalotti.aplicacaograjas.R;

public class FinanceiroActivity extends AppCompatActivity {

    private RecyclerView dadosFinanceiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_financeiro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Lista Financeiro");

        dadosFinanceiro= (RecyclerView)findViewById(R.id.dadosFinanceiro);

        dadosFinanceiro.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        dadosFinanceiro.setLayoutManager(linearLayoutManager);

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
        dadosFinanceiro.setAdapter(new FinanceiroAdapter(new FinanceiroBD().getLista()));
    }

    public void abreNovoFinanceiro(View view) {
        Intent intent = new Intent(FinanceiroActivity.this, EditarFinanceiroActivity.class);
        startActivityForResult(intent, 0);
    }

    @Override
    public void onBackPressed() {
    }
}
