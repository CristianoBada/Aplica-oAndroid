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
import com.cristianobadalotti.aplicacaograjas.Entidades.Racao;
import com.cristianobadalotti.aplicacaograjas.Entidades.TipoAve;
import com.cristianobadalotti.aplicacaograjas.EntidadesBanco.RacaoBD;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Calendario;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.MetodosComuns;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_racoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Eição de Lotes de Rações");

        racao = new Racao();

        calendario = new Calendario();

        editQuantidade = (EditText)findViewById(R.id.editTextQuantidadeRacao);
        editTipo = (EditText)findViewById(R.id.editTextTipoRacao);

        editData= (EditText)findViewById(R.id.editTextDataRacao);

        editData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(DATE_ID);
            }
        });

        verificaParametros();
    }

    public void salvarRacao(View view) {
        if (!validaCampos()) {
            this.racao.setTipoRacao(this.editTipo.getText().toString());
            this.racao.setQuantidade(Integer.parseInt(this.editQuantidade.getText().toString()));
            this.racao.setDataEntrada(this.editData.getText().toString());

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

    public void excluirRacao(View view) {
        new RacaoBD().apagar(this.racao.getCodigoRacao());
        finish();
    }
}
