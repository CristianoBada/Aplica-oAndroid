package com.cristianobadalotti.aplicacaograjas.Forms;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.cristianobadalotti.aplicacaograjas.Entidades.Vacina;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.VacinaBD;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Calendario;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Validacoes;

public class EditarVacinasActivity extends AppCompatActivity {

    //Codigo do calendario
    private Calendario calendario;
    static final int DATE_ID = 0;
    //Codigo do calendario

    private EditText editData;
    private EditText editTipo;
    private EditText editDetalhe;

    private Vacina vacina;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_vacinas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Edição de Tratamentos");

        vacina = new Vacina();

        calendario = new Calendario();

        editDetalhe = (EditText) findViewById(R.id.editTextDetalheVacina);
        editTipo = (EditText) findViewById(R.id.editTextTipoVacina);

        editData = (EditText) findViewById(R.id.editTextDataVacina);

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_ID);
            }
        });

        verificaParametros();
    }

    public void salvarVacina(View view) {
        if (!validaCampos()) {
            criaProgress();
            this.vacina.setTipoTratamento(this.editTipo.getText().toString());
            this.vacina.setDetalhe(this.editDetalhe.getText().toString());
            this.vacina.setDataTratamento(this.editData.getText().toString());
            VacinaBD vacinaBD = new VacinaBD();
            vacinaBD.salvar(this.vacina);

            Toast.makeText(this, vacinaBD.get_menssagem(), Toast.LENGTH_LONG).show();
            if (vacinaBD.is_status()) {
                finish();
            }
        }
    }

    private void verificaParametros() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if ((bundle != null) && (bundle.containsKey("VACINA"))) {
                vacina = (Vacina) bundle.getSerializable("VACINA");

                this.editTipo.setText(vacina.getTipoTratamento());
                this.editDetalhe.setText(vacina.getDetalhe());
                this.editData.setText(vacina.getDataTratamento());
            } else {
                opcaoExcluirVacina();
            }
        } else {
            opcaoExcluirVacina();
        }
    }

    private void opcaoExcluirVacina() {
        Button button = (Button) findViewById(R.id.buttonExcluirVacina);
        button.setVisibility(View.INVISIBLE);
    }

    private boolean validaCampos() {

        String data = editData.getText().toString();
        String tipo = editTipo.getText().toString();

        boolean res;

        if (res = Validacoes.isCampoVazio(tipo)) {
            editTipo.requestFocus();
        } else {
            if (res = Validacoes.isCampoVazio(data)) {
                editData.requestFocus();
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

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    calendario.setmYearIni(year);
                    calendario.setmMonthIni(monthOfYear);
                    calendario.setmDayIni(dayOfMonth);
                    editData.setText(calendario.getText());
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, mDateSetListener, calendario.getsYearIni(), calendario.getsMonthIni(), calendario.getsDayIni());
    }

    public void excluirVacina(View view) {
        new VacinaBD().apagar(this.vacina.getCodigoVacina());
        finish();
    }

    public void voltarVacina(View view) {
        criaProgress();
        finish();
    }

    @Override
    public void onBackPressed() {
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
