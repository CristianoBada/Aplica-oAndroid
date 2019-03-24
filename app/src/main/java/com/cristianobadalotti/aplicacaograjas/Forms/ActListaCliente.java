package com.cristianobadalotti.aplicacaograjas.Forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.cristianobadalotti.aplicacaograjas.Adapters.ClienteAdapter;
import com.cristianobadalotti.aplicacaograjas.Entidades.Cliente;
import com.cristianobadalotti.aplicacaograjas.R;

public class ActListaCliente extends AppCompatActivity {

    private RecyclerView dadosClientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_lista_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dadosClientes = (RecyclerView)findViewById(R.id.dadosClientes);

        dadosClientes.setHasFixedSize(true);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        dadosClientes.setLayoutManager(linearLayoutManager);

        ClienteAdapter clienteAdapter = new ClienteAdapter(new Cliente().getLista());

        dadosClientes.setAdapter(clienteAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        ClienteAdapter clienteAdapter = new ClienteAdapter(new Cliente().getLista());

        dadosClientes.setAdapter(clienteAdapter);
    }
}
