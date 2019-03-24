package com.cristianobadalotti.aplicacaograjas.Forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cristianobadalotti.aplicacaograjas.Entidades.Cliente;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Validacoes;

public class ActCadCliente extends AppCompatActivity {

    private Cliente cliente;

    private EditText editNome;
    private EditText editEndereco;
    private EditText editEmail;
    private EditText editTelefone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_cad_cliente);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        this.cliente = new Cliente();

        this.editNome = (EditText) findViewById(R.id.editNome);
        this.editEndereco = (EditText) findViewById(R.id.editEdereco);
        this.editEmail = (EditText) findViewById(R.id.editEmail);
        this.editTelefone = (EditText) findViewById(R.id.editiTelefone);

        verificaParametros();
    }

    private void verificaParametros() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if ((bundle != null) && (bundle.containsKey("CLIENTE"))) {
                cliente = (Cliente)bundle.getSerializable("CLIENTE");

                this.editNome.setText(cliente.getNome());
                this.editEndereco.setText(cliente.getEndereco());
                this.editEmail.setText(cliente.getEmail());
                this.editTelefone.setText(cliente.getTelefone());
            } else {
                opcaoExcluir();
            }
        } else {
            opcaoExcluir();
        }
    }

    private void opcaoExcluir() {
        Button button = (Button)findViewById(R.id.buttonExcluirCliente);
        button.setVisibility(View.INVISIBLE);
    }

    private boolean validaCampos() {
        String nome = editNome.getText().toString();
        String edereco = editEndereco.getText().toString();
        String email = editEmail.getText().toString();
        String telefone = editTelefone.getText().toString();

        boolean res = false;

        if (res = Validacoes.isCampoVazio(nome)) {
            editNome.requestFocus();
        } else {
            if (res = Validacoes.isCampoVazio(edereco)) {
                editEndereco.requestFocus();
            } else {
                if (res = !Validacoes.isEmailValido(email)) {
                    editEmail.requestFocus();
                } else {
                    if (res = Validacoes.isCampoVazio(telefone)) {
                        editTelefone.requestFocus();
                    }
                }
            }
        }

        if (res) {
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("Aviso");
            ab.setMessage("Há campos invalidos ou em  branco!");
            ab.setNeutralButton("OK", null);
            ab.show();
        }

        return res;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_act_cad_cliente, menu);

        return super.onCreateOptionsMenu(menu);
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

    public void salvarCliente(View view) {
        if (!validaCampos()) {
            this.cliente.setNome(this.editNome.getText().toString());
            this.cliente.setTelefone(this.editTelefone.getText().toString());
            this.cliente.setEmail(this.editEmail.getText().toString());
            this.cliente.setEndereco(this.editEndereco.getText().toString());
            this.cliente.salvar();

            Toast.makeText(this, this.cliente.get_menssagem(), Toast.LENGTH_LONG).show();
            if (cliente.is_status()) {
                finish();
            }
        }
    }

    public void excluirCliete(View view) {
        this.cliente.apagar();
        finish();
    }
}
