package com.cristianobadalotti.aplicacaograjas.Forms;

import android.app.DatePickerDialog;
import android.app.Dialog;
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

import com.cristianobadalotti.aplicacaograjas.Entidades.Ovos;
import com.cristianobadalotti.aplicacaograjas.Entidades.TipoAve;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.Calendario;
import com.cristianobadalotti.aplicacaograjas.Utilitarios.MetodosComuns;

import java.util.ArrayList;
import java.util.List;

public class EditarOvosActivity extends AppCompatActivity {

    //Codigo do calendario
    private Calendario calendario;
    static final int DATE_ID = 0;
    //Codigo do calendario

    private EditText editData;
    private Spinner spinnerTipoAve;
    private EditText editQuantidade;
    private EditText editLote;
    private EditText editQualidade;

    private Ovos ovos;

    private ArrayList<String> lista;
    private String tipoAve;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_ovos);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ovos = new Ovos();

        calendario = new Calendario();

        spinnerTipoAve = (Spinner) findViewById(R.id.spinnerTipoAveOvos);
        lista = new TipoAve().getListaAves();
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

        editLote = (EditText)findViewById(R.id.editTextLoteOvos);
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

    public void salvarOvos(View view) {
        if (!validaCampos()) {
            this.ovos.setTipoAve(this.tipoAve); //getSelectedItem().toString()
            this.ovos.setQuantidade(Integer.parseInt(this.editQuantidade.getText().toString()));
            this.ovos.setQualidade(this.editQualidade.getText().toString());
            this.ovos.setData(this.editData.getText().toString());
            this.ovos.setLote(this.editLote.getText().toString());
            this.ovos.salvar();

            Toast.makeText(this, this.ovos.get_menssagem(), Toast.LENGTH_LONG).show();
            if (ovos.is_status()) {
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
                this.editLote.setText(ovos.getLote());
                this.spinnerTipoAve.setSelection(MetodosComuns.achaPosicao(new TipoAve().getListaAves(), ovos.getTipoAve()));
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

    public void excluirOvos(View view) {
        this.ovos.apagar();
        finish();
    }

}
