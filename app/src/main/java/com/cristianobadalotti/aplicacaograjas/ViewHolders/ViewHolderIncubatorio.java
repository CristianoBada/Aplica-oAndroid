package com.cristianobadalotti.aplicacaograjas.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cristianobadalotti.aplicacaograjas.Entidades.Incubatorio;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarIncubatorioActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class ViewHolderIncubatorio extends RecyclerView.ViewHolder {

    public TextView textCodigoOvos;
    public TextView textTemperatura;
    public TextView textUmidade;
    public TextView textTempoChocar;
    public TextView textDataInicio;
    public TextView textMortalidade;
    public TextView textTipoAve;
    public TextView textCodigo;
    private ArrayList<Incubatorio> dados;

    public ViewHolderIncubatorio(@NonNull View itemView, final Context context) {
        super(itemView);

        textCodigoOvos = itemView.findViewById(R.id.textViewOvosPostura);
        textTemperatura = itemView.findViewById(R.id.textViewTemperaturaIncubatorio);
        textUmidade = itemView.findViewById(R.id.textViewUmidadeIncubatorio);
        textTempoChocar = itemView.findViewById(R.id.textViewTempChocaIncubatorio);
        textDataInicio = itemView.findViewById(R.id.textViewDataIncubatorio);
        textMortalidade = itemView.findViewById(R.id.textViewMortalidadeIncubatorio);
        textTipoAve = itemView.findViewById(R.id.textViewTipoAveIncubatorio);
        textCodigo = itemView.findViewById(R.id.textViewCodigoIncubatorio);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getDados().size() > 0) {

                    Incubatorio incubatorio = getDados().get(getLayoutPosition());

                    Intent intent = new Intent(context, EditarIncubatorioActivity.class);

                    intent.putExtra("INCUBATORIO", incubatorio);

                    ((AppCompatActivity) context).startActivityForResult(intent, 0);
                }
            }
        });
    }

    public ArrayList<Incubatorio> getDados() {
        return dados;
    }

    public void setDados(ArrayList<Incubatorio> dados) {
        this.dados = dados;
    }
}
