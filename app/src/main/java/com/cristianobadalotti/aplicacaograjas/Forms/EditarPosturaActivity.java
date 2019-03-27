package com.cristianobadalotti.aplicacaograjas.Forms;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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

import com.cristianobadalotti.aplicacaograjas.Entidades.Postura;
import com.cristianobadalotti.aplicacaograjas.Entidades.TipoAve;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.PosturaBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.TipoAveBD;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Calendario;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.MetodosComuns;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Validacoes;

import java.util.ArrayList;
import java.util.List;

public class EditarPosturaActivity extends AppCompatActivity {

    //Codigo do calendario
    static final int DATE_ID = 0;
    static final int DATE_ID2 = 1;
    private int id_date;
    private Calendario calendario;
    //Codigo do calendario


    private EditText editDataEntrada;
    private EditText editDataSaida;
    private Spinner spinnerTipoAve;
    private EditText editQuantidade;
    private EditText editMaxAves;
    private EditText editObservacao;

    private Postura postura;

    private ArrayList<String> lista;
    private String tipoAve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_postura);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        postura = new Postura();

        calendario = new Calendario();

        spinnerTipoAve = (Spinner) findViewById(R.id.spinnerTipoAve);
        lista = new TipoAveBD().getListaAves();
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

        editMaxAves = (EditText)findViewById(R.id.editTextMaxAves);
        editObservacao = (EditText)findViewById(R.id.editTextObservacao);
        editQuantidade = (EditText)findViewById(R.id.editTextQuantidade);

        editDataEntrada = (EditText)findViewById(R.id.editTextDataEntrada);
        editDataSaida = (EditText)findViewById(R.id.editTextDataSaida);

        editDataEntrada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_date = DATE_ID;
                showDialog(DATE_ID);
            }
        });

        editDataSaida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id_date = DATE_ID2;
                showDialog(DATE_ID2);
            }
        });

        verificaParametros();
    }

    public void salvarPostura(View view) {
        if (!validaCampos()) {
            this.postura.setTipoAve(this.tipoAve); //getSelectedItem().toString()
            this.postura.setQuantidade(Integer.parseInt(this.editQuantidade.getText().toString()));
            this.postura.setMaximoAves(Integer.parseInt(this.editMaxAves.getText().toString()));
            this.postura.setDatasaida(this.editDataSaida.getText().toString());
            this.postura.setDataentrada(this.editDataEntrada.getText().toString());
            this.postura.setComentario(this.editObservacao.getText().toString());
            PosturaBD posturaBD = new PosturaBD();
            posturaBD.salvar(this.postura);

            Toast.makeText(this, posturaBD.get_menssagem(), Toast.LENGTH_LONG).show();
            if (posturaBD.is_status()) {
                finish();
            }
        }
    }

    private void verificaParametros() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if ((bundle != null) && (bundle.containsKey("POSTURA"))) {
                postura = (Postura) bundle.getSerializable("POSTURA");

                this.editMaxAves.setText(postura.getMaximoAves()+"");
                this.editQuantidade.setText(postura.getQuantidade()+"");
                this.editDataSaida.setText(postura.getDatasaida());
                this.editDataEntrada.setText(postura.getDatasaida());
                this.editObservacao.setText(postura.getComentario());
                this.spinnerTipoAve.setSelection(MetodosComuns.achaPosicao(new TipoAveBD().getListaAves(), postura.getTipoAve()));
            } else {
                opcaoExcluir();
            }
        } else {
            opcaoExcluir();
        }
    }

    private void opcaoExcluir() {
        Button button = (Button)findViewById(R.id.buttonExcluirPostura);
        button.setVisibility(View.INVISIBLE);
    }

    private boolean validaCampos() {

        String quant = editQuantidade.getText().toString();
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

        return res;
    }

    //Código do calendario
    private void colocar_fecha() {
        if (id_date == DATE_ID){
            editDataEntrada.setText(calendario.getText());
        } else {
            editDataSaida.setText(calendario.getText());
        }
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    calendario.setmYearIni(year);
                    calendario.setmMonthIni(monthOfYear);
                    calendario.setmDayIni(dayOfMonth);
                    colocar_fecha();
                }
            };

    @Override
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, mDateSetListener, calendario.getsYearIni(), calendario.getsMonthIni(), calendario.getsDayIni());
    }

    public void excluirPostura(View view) {
        PosturaBD posturaBD = new PosturaBD();
        posturaBD.apagar(this.postura);
        finish();
    }
}
