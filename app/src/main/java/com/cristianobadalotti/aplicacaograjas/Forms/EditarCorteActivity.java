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

import com.cristianobadalotti.aplicacaograjas.Entidades.Corte;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.CorteBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.RacaoBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.TipoAveBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.VacinaBD;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Calendario;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Conversoes;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.MetodosComuns;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Validacoes;

import java.util.ArrayList;
import java.util.List;

public class EditarCorteActivity extends AppCompatActivity {

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
    private EditText editMortalidade;

    private Corte corte;

    private ArrayList<String> lista;
    private String tipoAve;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_corte);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Edição de Granja de Corte");

        corte = new Corte();

        calendario = new Calendario();

        spinnerTipoAve = (Spinner) findViewById(R.id.spinnerTipoAveCorte);
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

        editMaxAves = (EditText)findViewById(R.id.editTextMaxCorte);
        editObservacao = (EditText)findViewById(R.id.editTextComentarioCorte);
        editQuantidade = (EditText)findViewById(R.id.editTextQuantidadeCorte);
        editMortalidade = (EditText)findViewById(R.id.editTextMortalidadeCorte);

        editDataEntrada = (EditText)findViewById(R.id.editTextDataEntradaCorte);
        editDataEntrada.setKeyListener(null);
        editDataSaida = (EditText)findViewById(R.id.editTextDataSaidaCorte);
        editDataSaida.setKeyListener(null);

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

    public void salvarCorte(View view) {
        if (!validaCampos()) {
            criaProgress();
            this.corte.setTipoAve(this.tipoAve); //getSelectedItem().toString()
            this.corte.setQuantidadeAves(Integer.parseInt(this.editQuantidade.getText().toString()));
            this.corte.setMaximo(Integer.parseInt(this.editMaxAves.getText().toString()));
            this.corte.setDataSaida(this.editDataSaida.getText().toString());
            this.corte.setDataEntrada(this.editDataEntrada.getText().toString());
            this.corte.setComentario(this.editObservacao.getText().toString());
            if (!this.editMortalidade.getText().toString().isEmpty()) {
                this.corte.setMortalidade(Integer.parseInt(this.editMortalidade.getText().toString()));
            }
            CorteBD corteBD = new CorteBD();
            corteBD.salvar(this.corte);

            Toast.makeText(this, corteBD.get_menssagem(), Toast.LENGTH_LONG).show();
            if (corteBD.is_status()) {
                finish();
            }
        }
    }

    private void verificaParametros() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if ((bundle != null) && (bundle.containsKey("CORTE"))) {
                corte = (Corte) bundle.getSerializable("CORTE");

                this.editMaxAves.setText(corte.getMaximo()+"");
                this.editQuantidade.setText(corte.getQuantidadeAves()+"");
                this.editDataSaida.setText(corte.getDataSaida());
                this.editDataEntrada.setText(corte.getDataEntrada());
                this.editObservacao.setText(corte.getComentario());
                this.spinnerTipoAve.setSelection(MetodosComuns.achaPosicao(new TipoAveBD().getListaString(), corte.getTipoAve()));
                this.editMortalidade.setText(this.corte.getMortalidade()+"");
            } else {
                opcaoExcluir();
            }
        } else {
            opcaoExcluir();
        }
    }

    private void opcaoExcluir() {
        Button button = (Button)findViewById(R.id.buttonExcluirCorte);
        button.setVisibility(View.INVISIBLE);
    }

    private boolean validaCampos() {

        String quant = editQuantidade.getText().toString();
        String max = editMaxAves.getText().toString();
        String dataEntrada = editDataEntrada.getText().toString();
        String datasaida = editDataSaida.getText().toString();

        boolean res = false;
        String msg = "";
        if (res = Validacoes.isCampoVazio(quant)) {
            editQuantidade.requestFocus();
            msg = "Há campos invalidos ou em  branco!";
        } else {
            if (res = Validacoes.isCampoVazio(max)) {
                editMaxAves.requestFocus();
                msg = "Há campos invalidos ou em  branco!";
            } else {
                if (res = Validacoes.isCampoVazio(dataEntrada)) {
                    editDataEntrada.requestFocus();
                    msg = "Há campos invalidos ou em  branco!";
                } else {
                    int q = Integer.parseInt(quant);
                    int m = Integer.parseInt(max);
                    if (q > m) {
                        msg = "Numero maximo de aves não pode ser inferior a quantidade.";
                        res = true;
                        editMaxAves.requestFocus();
                    } else {
                        Conversoes conversoes = new Conversoes();
                        if (!Validacoes.isCampoVazio(datasaida) && conversoes.comparaDatas(dataEntrada, datasaida)) {
                            editDataSaida.requestFocus();
                            res = true;
                            msg = "Data saida tem que ser uma data superior a data de entrada.";
                        }
                    }
                }
            }
        }

        if (res) {
            criaMsg(msg);
        }

        return res;
    }

    private void criaMsg(String msg) {
        AlertDialog.Builder ab = new AlertDialog.Builder(this);
        ab.setTitle("Aviso");
        ab.setMessage(msg);
        ab.setNeutralButton("OK", null);
        ab.show();
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

    public void excluirCorte(View view) {
        if (new RacaoBD().isUserCorte(this.corte.getCodigoCorte())){
            criaMsg("Esse lote esta sendo usado em um lote de Ração.");
        } else {
            if (new VacinaBD().isUserCorte(this.corte.getCodigoCorte())) {
                criaMsg("Esse lote esta sendo usado em um tratamento.");
            } else {
                criaProgress();
                new CorteBD().apagar(this.corte.getCodigoCorte());
                finish();
            }
        }
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
