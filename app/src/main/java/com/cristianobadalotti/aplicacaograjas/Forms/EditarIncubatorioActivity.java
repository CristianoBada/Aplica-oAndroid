package com.cristianobadalotti.aplicacaograjas.Forms;

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
import android.support.v7.app.AlertDialog;

import com.cristianobadalotti.aplicacaograjas.Entidades.Incubatorio;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.IncubatorioBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.OvosBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.TipoAveBD;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Calendario;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.MetodosComuns;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Validacoes;

import java.util.ArrayList;
import java.util.List;

public class EditarIncubatorioActivity extends AppCompatActivity {

    //Codigo do calendario
    private Calendario calendario;
    static final int DATE_ID = 0;
    //Codigo do calendario

    private EditText editData;
    private Spinner spinnerOvos;
    private EditText editTemperatura;
    private EditText editUmidade;
    private EditText editMortalidade;

    private Incubatorio incubatorio;

    private ArrayList<String> lista;
    private ProgressDialog progressDialog;
    private int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_incubatorio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Edição de Incubatório");

        incubatorio = new Incubatorio();

        calendario = new Calendario();

        criaListaOvos();

        editTemperatura = (EditText) findViewById(R.id.editTextTemperaturaIncubatorio);
        editUmidade = (EditText) findViewById(R.id.editTextUmidadeIncubatorio);
        editMortalidade = (EditText) findViewById(R.id.editTextMortalidadeIncubatorio);

        editData = (EditText) findViewById(R.id.editTextDataIncubatorio);
        editData.setKeyListener(null);

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_ID);
            }
        });

        verificaParametros();
    }

    private void criaListaOvos() {
        spinnerOvos = (Spinner) findViewById(R.id.spinnerOvosIncubatorio);
        lista = new OvosBD().getListaString();
        List<String> list = lista;
        pos = 0;

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerOvos.setAdapter(dataAdapter);

        spinnerOvos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                pos = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void salvarIncubatorio(View view) {
        if (!validaCampos()) {
            criaProgress();
            this.incubatorio.setCodigoOvos(new OvosBD().getLista().get(pos).getCodigo());
            if (!this.editUmidade.getText().toString().isEmpty()) {
                this.incubatorio.setUmidade(Integer.parseInt(this.editUmidade.getText().toString()));
            }
            this.incubatorio.setTipoAve(new OvosBD().getLista().get(pos).getTipoAve());
            this.incubatorio.setTemperatura(Double.parseDouble(this.editTemperatura.getText().toString()));
            if (!this.editMortalidade.getText().toString().isEmpty()) {
                this.incubatorio.setMortalidade(Integer.parseInt(this.editMortalidade.getText().toString()));
            }
            this.incubatorio.setDataInicio(this.editData.getText().toString());
            String tipoAve = new OvosBD().getLista().get(pos).getTipoAve();
            int tempoC = new TipoAveBD().getTempoChocar(tipoAve);
            this.incubatorio.setTempoChocar(tempoC);

            IncubatorioBD incubatorioBD = new IncubatorioBD();
            incubatorioBD.salvar(this.incubatorio);

            Toast.makeText(this, incubatorioBD.get_menssagem(), Toast.LENGTH_LONG).show();
            if (incubatorioBD.is_status()) {
                finish();
            }
        }
    }

    private void verificaParametros() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if ((bundle != null) && (bundle.containsKey("INCUBATORIO"))) {
                incubatorio = (Incubatorio) bundle.getSerializable("INCUBATORIO");

                this.editData.setText(incubatorio.getDataInicio() + "");
                this.editMortalidade.setText(incubatorio.getMortalidade() + "");
                this.editTemperatura.setText(incubatorio.getTemperatura() + "");
                this.editUmidade.setText(incubatorio.getUmidade() + "");
                this.spinnerOvos.setSelection(MetodosComuns.achaPosicao(new OvosBD().getListaString(), incubatorio.getCodigoOvos() + " " + incubatorio.getTipoAve()));
            } else {
                opcaoExcluirIncubatorio();
            }
        } else {
            opcaoExcluirIncubatorio();
        }
    }

    private void opcaoExcluirIncubatorio() {
        Button button = (Button) findViewById(R.id.buttonExcluirIncubatorio);
        button.setVisibility(View.INVISIBLE);
    }

    private boolean validaCampos() {

        String temperatura = editTemperatura.getText().toString();
        String data = editData.getText().toString();

        boolean res = false;
        String msg = "";

        if (res = Validacoes.isCampoVazio(temperatura)) {
            editTemperatura.requestFocus();
            msg = "Há campos invalidos ou em  branco!";
        } else {
            if (res = Validacoes.isCampoVazio(data)) {
                editData.requestFocus();
                msg = "Há campos invalidos ou em  branco!";
            } else {
                Double tempe = Double.parseDouble(temperatura);
                if (tempe > 38.0 ||  tempe < 36.8) {
                    editTemperatura.requestFocus();
                    msg = "A temperatura tem que ser entre 36,8°C e 38°C. Recomenda-se entre 37,4°C e 37,8°C!";
                    res = true;
                } else {
                    String um = editUmidade.getText().toString();
                    if (!Validacoes.isCampoVazio(um)) {
                        int umidade = Integer.parseInt(um);
                        if (umidade != 0 && (umidade < 65 || umidade > 75)) {
                            editUmidade.requestFocus();
                            msg = "A umidade relativa do ar deve se manter entre 65% e 75%.";
                            res = true;
                        }
                    }

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

    public void excluirIncubatorio(View view) {
        criaProgress();
        new IncubatorioBD().apagar(this.incubatorio.getCodigoIncubatorio());
        finish();
    }

    @Override
    public void onBackPressed() {
        criaProgress();
        finish();
    }

    public void voltarMenu(View view) {
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
