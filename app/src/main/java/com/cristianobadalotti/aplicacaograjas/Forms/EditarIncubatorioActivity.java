package com.cristianobadalotti.aplicacaograjas.Forms;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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

import com.cristianobadalotti.aplicacaograjas.Entidades.Incubatorio;
import com.cristianobadalotti.aplicacaograjas.Entidades.TipoAve;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.IncubatorioBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.TipoAveBD;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Calendario;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.MetodosComuns;

import java.util.ArrayList;
import java.util.List;

public class EditarIncubatorioActivity extends AppCompatActivity {

    //Codigo do calendario
    private Calendario calendario;
    static final int DATE_ID = 0;
    //Codigo do calendario

    private EditText editData;
    private Spinner spinnerTipoAve;
    private EditText editTemperatura;
    private EditText editLoteOvos;
    private EditText editUmidade;
    private EditText editMortalidade;
    private EditText editTempo;

    private Incubatorio incubatorio;

    private ArrayList<String> lista;
    private String tipoAve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_incubatorio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        incubatorio = new Incubatorio();

        calendario = new Calendario();

        spinnerTipoAve = (Spinner) findViewById(R.id.spinnerTipoAveIncubatorio);
        lista = new TipoAveBD().getListaString();
        List<String> list = lista;
        tipoAve = lista.get(0);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerTipoAve.setAdapter(dataAdapter);

        spinnerTipoAve.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                tipoAve = lista.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        editLoteOvos = (EditText)findViewById(R.id.editTextLoteOvosIncubatorio);
        editTemperatura = (EditText)findViewById(R.id.editTextTemperaturaIncubatorio);
        editLoteOvos = (EditText)findViewById(R.id.editTextLoteOvosIncubatorio);
        editUmidade = (EditText)findViewById(R.id.editTextUmidadeIncubatorio);
        editMortalidade = (EditText)findViewById(R.id.editTextMortalidadeIncubatorio);
        editTempo = (EditText)findViewById(R.id.editTextTemperaturaIncubatorio);

        editData= (EditText)findViewById(R.id.editTextDataIncubatorio);

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_ID);
            }
        });

        verificaParametros();
    }

    public void salvarIncubatorio(View view) {
        if (!validaCampos()) {
            this.incubatorio.setLoteOvos(this.editLoteOvos.getText().toString());
            this.incubatorio.setUmidade(Integer.parseInt(this.editUmidade.getText().toString()));
            this.incubatorio.setTipoAve(this.tipoAve);
            this.incubatorio.setTempoChocar(Integer.parseInt(this.editTempo.getText().toString()));
            this.incubatorio.setTemperatura(Integer.parseInt(this.editTemperatura.getText().toString()));
            this.incubatorio.setMortalidade(Integer.parseInt(this.editMortalidade.getText().toString()));
            this.incubatorio.setDataInicio(this.editData.getText().toString());
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

                this.editLoteOvos.setText(incubatorio.getLoteOvos()+"");
                this.editData.setText(incubatorio.getDataInicio()+"");
                this.editMortalidade.setText(incubatorio.getMortalidade()+"");
                this.editTemperatura.setText(incubatorio.getTemperatura()+"");
                this.editTempo.setText(incubatorio.getTempoChocar()+"");
                this.editUmidade.setText(incubatorio.getUmidade()+"");
                this.spinnerTipoAve.setSelection(MetodosComuns.achaPosicao(new TipoAveBD().getListaString(), incubatorio.getTipoAve()));
            } else {
                opcaoExcluirIncubatorio();
            }
        } else {
            opcaoExcluirIncubatorio();
        }
    }

    private void opcaoExcluirIncubatorio() {
        Button button = (Button)findViewById(R.id.buttonExcluirIncubatorio);
        button.setVisibility(View.INVISIBLE);
    }

    private boolean validaCampos() {

      /*  String quant = editQuantidade.getText().toString();
        String max = editMaxAves.getText().toString();
        String dataEntrada = editDataEntrada.getText().toString();

        boolean res = false;

        if (res = Validacoes.isCampoVazio(quant)) {
            editQuantidade.requestFocus();
        } else {
            if (res = Validacoes.isCampoVazio(max)) {
                editMaxAves.requestFocus();
            } else {
                if (res = Validacoes.isCampoVazio(dataEntrada)) {
                    editDataEntrada.requestFocus();
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

        return res;*/
        return false;
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
        new IncubatorioBD().apagar(this.incubatorio.getCodigoIncubatorio());
        finish();
    }
}
