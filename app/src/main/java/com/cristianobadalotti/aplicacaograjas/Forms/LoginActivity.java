package com.cristianobadalotti.aplicacaograjas.Forms;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.cristianobadalotti.aplicacaograjas.Entidades.Usuario;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.UsuarioBD;
import com.cristianobadalotti.aplicacaograjas.R;

public class LoginActivity extends AppCompatActivity {

    private Button buttonEntrar;
    private EditText editUsuario;
    private EditText editSenha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("ASF - AVICULTURA");

        buttonEntrar = (Button)findViewById(R.id.buttonEntrar);
        editSenha = (EditText)findViewById(R.id.editTextSenha);
        editUsuario = (EditText)findViewById(R.id.editTextUsuario);
    }

    public void entraNoSistema(View view) {
        UsuarioBD bd = new UsuarioBD();
        boolean valido = bd.existeUsuario(editUsuario.getText().toString(), editSenha.getText().toString());
        if (valido) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        } else {
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("Aviso");
            ab.setMessage("Usuario ou/e Senha invalidos!");
            ab.setNeutralButton("OK", null);
            ab.show();
        }
    }

}
