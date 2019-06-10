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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.cristianobadalotti.aplicacaograjas.Entidades.Vacina;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.CorteBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.PosturaBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.VacinaBD;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Calendario;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.MetodosComuns;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Validacoes;

import java.util.ArrayList;
import java.util.List;

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

    private Spinner spinnerPostura;
    private ArrayList<String> lista;
    private int pos = 0;

    private Spinner spinnerCorte;
    private ArrayList<String> lista2;
    private int pos2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_vacinas);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Edição de Tratamentos");

        vacina = new Vacina();

        calendario = new Calendario();

        criaListaPostura();
        criaListaCorte();

        editDetalhe = (EditText) findViewById(R.id.editTextDetalheVacina);
        editTipo = (EditText) findViewById(R.id.editTextTipoVacina);

        editData = (EditText) findViewById(R.id.editTextDataVacina);
        editData.setKeyListener(null);

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_ID);
            }
        });

        verificaParametros();
    }

    private void criaListaPostura(){
        lista = new PosturaBD().getListaString(false);
        spinnerPostura = (Spinner) findViewById(R.id.spinnerVacinaPostura);
        lista.add(0, "0");
        List<String> list = lista;
        pos = 0;

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerPostura.setAdapter(dataAdapter2);

        spinnerPostura.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void criaListaCorte(){
        lista2 = new CorteBD().getListaString();
        spinnerCorte = (Spinner) findViewById(R.id.spinnerVacinaCorte);
        lista2.add(0, "0");
        List<String> list = lista2;
        pos2 = 0;

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerCorte.setAdapter(dataAdapter2);

        spinnerCorte.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    public void salvarVacina(View view) {
        if (!validaCampos()) {
            criaProgress();
            this.vacina.setTipoTratamento(this.editTipo.getText().toString());
            this.vacina.setDetalhe(this.editDetalhe.getText().toString());
            this.vacina.setDataTratamento(this.editData.getText().toString());
            if (pos >0) {
                this.vacina.setCodigoPostura(new PosturaBD().getLista().get(pos - 1).getCodigoPostura());
            } else {
                this.vacina.setCodigoPostura(pos);
            }
            if (pos2 >0) {
                this.vacina.setCodigoCorte(new CorteBD().getLista().get(pos2 -1).getCodigoCorte());
            } else {
                this.vacina.setCodigoCorte(pos2);
            }

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
                this.spinnerPostura.setSelection(MetodosComuns.achaPosicao(lista, vacina.getCodigoPostura() + ""));
                this.spinnerCorte.setSelection(MetodosComuns.achaPosicao(lista2, vacina.getCodigoCorte() + ""));
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
        String msg = "";
        if (res = Validacoes.isCampoVazio(tipo)) {
            editTipo.requestFocus();
            msg = "Há campos invalidos ou em  branco!";
        } else {
            if (res = Validacoes.isCampoVazio(data)) {
                editData.requestFocus();
                msg = "Há campos invalidos ou em  branco!";
            } else {
                if (pos == 0 && pos2 == 0) {
                    msg = "Um tratamento deve ser atribuido para um Lote de Postura ou Corte.";
                    res = true;
                }
            }
        }

        if (res) {
            AlertDialog.Builder ab = new AlertDialog.Builder(this);
            ab.setTitle("Aviso");
            ab.setMessage(msg);
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
