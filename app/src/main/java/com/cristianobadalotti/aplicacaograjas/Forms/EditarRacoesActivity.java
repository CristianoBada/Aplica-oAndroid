package com.cristianobadalotti.aplicacaograjas.Forms;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
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
import com.cristianobadalotti.aplicacaograjas.Entidades.Racao;
import com.cristianobadalotti.aplicacaograjas.Entidades.TipoAve;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.CorteBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.PosturaBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.RacaoBD;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Calendario;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.MetodosComuns;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Validacoes;

import java.util.ArrayList;
import java.util.List;

public class EditarRacoesActivity extends AppCompatActivity {

    //Codigo do calendario
    private Calendario calendario;
    static final int DATE_ID = 0;
    //Codigo do calendario

    private EditText editData;
    private EditText editQuantidade;
    private EditText editTipo;

    private Racao racao;
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
        setContentView(R.layout.activity_editar_racoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Eição de Lotes de Rações");

        racao = new Racao();

        calendario = new Calendario();

        criaListaPostura();
        criaListaCorte();

        editQuantidade = (EditText)findViewById(R.id.editTextQuantidadeRacao);
        editTipo = (EditText)findViewById(R.id.editTextTipoRacao);

        editData= (EditText)findViewById(R.id.editTextDataRacao);
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
        spinnerPostura = (Spinner) findViewById(R.id.spinnerRacaoPostura);
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
        spinnerCorte = (Spinner) findViewById(R.id.spinnerRacaoCorte);
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

    public void salvarRacao(View view) {
        if (!validaCampos()) {
            criaProgress();
            this.racao.setTipoRacao(this.editTipo.getText().toString());
            this.racao.setQuantidade(Integer.parseInt(this.editQuantidade.getText().toString()));
            this.racao.setDataEntrada(this.editData.getText().toString());
            if (pos >0) {
                this.racao.setCodigoPostura(new PosturaBD().getLista().get(pos - 1).getCodigoPostura());
            } else {
                this.racao.setCodigoPostura(pos);
            }
            if (pos2 >0) {
                this.racao.setCodigoCorte(new CorteBD().getLista().get(pos2 -1).getCodigoCorte());
            } else {
                this.racao.setCodigoCorte(pos2);
            }

            RacaoBD racaoBD = new RacaoBD();
            racaoBD.salvar(this.racao);

            Toast.makeText(this, racaoBD.get_menssagem(), Toast.LENGTH_LONG).show();
            if (racaoBD.is_status()) {
                finish();
            }
        }
    }

    private void verificaParametros() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if ((bundle != null) && (bundle.containsKey("RACAO"))) {
                this.racao = (Racao) bundle.getSerializable("RACAO");

                this.editTipo.setText(racao.getTipoRacao()+"");
                this.editData.setText(racao.getDataEntrada()+"");
                this.editQuantidade.setText(racao.getQuantidade()+"");
                this.spinnerPostura.setSelection(MetodosComuns.achaPosicao(lista, racao.getCodigoPostura() + ""));
                this.spinnerCorte.setSelection(MetodosComuns.achaPosicao(lista2, racao.getCodigoCorte() + ""));
            } else {
                opcaoExcluirRacao();
            }
        } else {
            opcaoExcluirRacao();
        }
    }

    private void opcaoExcluirRacao() {
        Button button = (Button)findViewById(R.id.buttonExcluirRacao);
        button.setVisibility(View.INVISIBLE);
    }

    private boolean validaCampos() {
        String tipo = editTipo.getText().toString();
        String quant = editQuantidade.getText().toString();
        String data = editData.getText().toString();

        boolean res = false;
        String msg = "";

        if (res = Validacoes.isCampoVazio(tipo)) {
            editTipo.requestFocus();
            msg = "Há campos invalidos ou em  branco!";
        } else {
            if (res = Validacoes.isCampoVazio(quant)) {
                editQuantidade.requestFocus();
                msg = "Há campos invalidos ou em  branco!";
            } else {
                if (res = Validacoes.isCampoVazio(data)) {
                    editData.requestFocus();
                    msg = "Há campos invalidos ou em  branco!";
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

    public void excluirRacao(View view) {
        criaProgress();
        new RacaoBD().apagar(this.racao.getCodigoRacao());
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
