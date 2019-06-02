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

import com.cristianobadalotti.aplicacaograjas.Entidades.Ovos;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.OvosBD;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.PosturaBD;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Calendario;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.MetodosComuns;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Validacoes;

import java.util.ArrayList;
import java.util.List;

public class EditarOvosActivity extends AppCompatActivity {

    //Codigo do calendario
    private Calendario calendario;
    static final int DATE_ID = 0;
    //Codigo do calendario

    private EditText editData;
    private Spinner spinnerPostura;
    private EditText editQuantidade;
    private EditText editQualidade;

    private Ovos ovos;

    private ArrayList<String> lista;
    private int pos = 0;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ovos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Edição de Lote de Ovos");

        ovos = new Ovos();

        calendario = new Calendario();

        criaListaPostura();

        editQualidade = (EditText)findViewById(R.id.editTextQualidadeOvos);
        editQuantidade = (EditText)findViewById(R.id.editTextQuantidadeOvos);

        editData= (EditText)findViewById(R.id.editTextDataOvos);

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_ID);
            }
        });

        verificaParametros();

    }

    private void criaListaPostura(){
        lista = new PosturaBD().getListaString();
        spinnerPostura = (Spinner) findViewById(R.id.spinnerOPostura);
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

    public void salvarOvos(View view) {
        if (!validaCampos()) {
            criaProgress();
            this.ovos.setTipoAve(new PosturaBD().getLista().get(pos).getTipoAve()); //getSelectedItem().toString()
            this.ovos.setQuantidade(Integer.parseInt(this.editQuantidade.getText().toString()));
            this.ovos.setQualidade(this.editQualidade.getText().toString());
            this.ovos.setData(this.editData.getText().toString());
            this.ovos.setPostura(new PosturaBD().getLista().get(pos).getCodigoPostura());
            OvosBD ovosBD = new OvosBD();
            ovosBD.salvar(this.ovos);

            Toast.makeText(this, ovosBD.get_menssagem(), Toast.LENGTH_LONG).show();
            if (ovosBD.is_status()) {
                finish();
            }
        }
    }

    private void verificaParametros() {
        Intent intent = getIntent();
        if (intent != null) {
            Bundle bundle = intent.getExtras();
            if ((bundle != null) && (bundle.containsKey("OVOS"))) {
                ovos = (Ovos) bundle.getSerializable("OVOS");

                this.editQualidade.setText(ovos.getQualidade()+"");
                this.editQuantidade.setText(ovos.getQuantidade()+"");
                this.editData.setText(ovos.getData());
                this.spinnerPostura.setSelection(MetodosComuns.achaPosicao(lista, ovos.getPostura() + " " + ovos.getTipoAve()));
            } else {
                opcaoExcluirOvos();
            }
        } else {
            opcaoExcluirOvos();
        }
    }

    private void opcaoExcluirOvos() {
        Button button = (Button)findViewById(R.id.buttonExcluirOvos);
        button.setVisibility(View.INVISIBLE);
    }

    private boolean validaCampos() {

        String data = editData.getText().toString();
        String quant = editQuantidade.getText().toString();


        boolean res = false;


            if (res = Validacoes.isCampoVazio(data)) {
                editData.requestFocus();
            } else {
                if (res = Validacoes.isCampoVazio(quant)) {
                    editQuantidade.requestFocus();
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

    public void excluirOvos(View view) {
        criaProgress();
        new OvosBD().apagar(this.ovos.getCodigo());
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
