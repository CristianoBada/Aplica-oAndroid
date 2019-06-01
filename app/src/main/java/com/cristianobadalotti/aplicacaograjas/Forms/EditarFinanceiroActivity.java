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

import com.cristianobadalotti.aplicacaograjas.Entidades.Financeiro;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.FinanceiroBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.ProdutoBD;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Calendario;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.MetodosComuns;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Validacoes;

import java.util.ArrayList;
import java.util.List;

public class EditarFinanceiroActivity extends AppCompatActivity {

    private EditText editValor;
    private Spinner spinnerProdutos;
    private Spinner spinnerTrasacao;
    private String trasacao;
    private EditText editDetalhe;

    private Financeiro financeiro;

    private ArrayList<String> listaTrasacao;
    private ArrayList<String> listaProdutos;
    private String produto;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_financeiro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Edição Financeiro");

        financeiro = new Financeiro();

        criaListaProdutos();
        criaListaTrasacao();

        editDetalhe = (EditText)findViewById(R.id.editTextDetalheFinanceiro);
        editValor = (EditText)findViewById(R.id.editTextValorFinanceiro);

        verificaParametros();
    }

    private void criaListaProdutos() {
        spinnerProdutos = (Spinner) findViewById(R.id.spinnerProdutoFinanceiro);
        listaProdutos = new ProdutoBD().getListaString();
        List<String> list = listaProdutos;
        produto = listaProdutos.get(0);

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerProdutos.setAdapter(dataAdapter);

        spinnerProdutos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                produto = listaProdutos.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void criaListaTrasacao() {
        spinnerTrasacao = (Spinner) findViewById(R.id.spinnerEntradaFinanceiro);
        listaTrasacao = new ArrayList<>();
        listaTrasacao.add("COMPRA");
        listaTrasacao.add("VENDA");

        List<String> list = listaTrasacao;
        trasacao = list.get(0);

        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, list);
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_1);
        spinnerTrasacao.setAdapter(dataAdapter2);

        spinnerTrasacao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                trasacao = listaTrasacao.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void salvarFinanceiro(View view) {
        if (!validaCampos()) {
            criaProgress();
            this.financeiro.setValor(Double.parseDouble(this.editValor.getText().toString()));
            this.financeiro.setNome(this.produto);
            this.financeiro.setDetalhe(this.editDetalhe.getText().toString());
            this.financeiro.setEntrasaida(this.trasacao);
            FinanceiroBD financeiroBD = new FinanceiroBD();
            financeiroBD.salvar(this.financeiro);

            Toast.makeText(this, financeiroBD.get_menssagem(), Toast.LENGTH_LONG).show();
            if (financeiroBD.is_status()) {
                finish();
            }
        }
    }

    private void verificaParametros() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if ((bundle != null) && (bundle.containsKey("FINANCEIRO"))) {
                financeiro = (Financeiro) bundle.getSerializable("FINANCEIRO");

                this.editValor.setText(financeiro.getValor() + "");
                this.editDetalhe.setText(financeiro.getDetalhe());
                this.spinnerProdutos.setSelection(MetodosComuns.achaPosicao(new ProdutoBD().getListaString(), financeiro.getNome()));
                this.spinnerTrasacao.setSelection(MetodosComuns.achaPosicao(listaTrasacao, financeiro.getEntrasaida()));

            } else {
                opcaoExcluirFinanceiro();
            }
        } else {
            opcaoExcluirFinanceiro();
        }
    }

    private void opcaoExcluirFinanceiro() {
        Button button = (Button)findViewById(R.id.buttonExcluirFinanceiro);
        button.setVisibility(View.INVISIBLE);
    }

    private boolean validaCampos() {

        String valor = editValor.getText().toString();

        boolean res = false;

        if (res = Validacoes.isCampoVazio(valor)) {
            editValor.requestFocus();
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

    public void excluirIncubatorio(View view) {
        criaProgress();
        new FinanceiroBD().apagar(this.financeiro.getCodigoFinanceiro());
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
