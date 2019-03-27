package com.cristianobadalotti.aplicacaograjas.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cristianobadalotti.aplicacaograjas.Entidades.Corte;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarCorteActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class ViewHolderCorte extends RecyclerView.ViewHolder {
    public TextView textQuantidade;
    public TextView textComentario;
    public TextView textMaxAves;
    public TextView textDataEntrada;
    public TextView textDataSaida;
    public TextView textTipoAve;
    public TextView textMortalidade;
    private ArrayList<Corte> dados;

    public ViewHolderCorte(@NonNull View itemView, final Context context) {
        super(itemView);

        textQuantidade = itemView.findViewById(R.id.textViewQuantidadeCorte);
        textComentario = itemView.findViewById(R.id.textViewComentarioCorte);
        textMaxAves = itemView.findViewById(R.id.textViewMaximoCorte);
        textDataEntrada = itemView.findViewById(R.id.textViewDataEntradaCorte);
        textDataSaida = itemView.findViewById(R.id.textViewDataSaidaCorte);
        textTipoAve = itemView.findViewById(R.id.textViewTipoAvesCorte);
        textMortalidade = itemView.findViewById(R.id.textViewMortalidadeCorte);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getDados().size() > 0) {

                    Corte corte = getDados().get(getLayoutPosition());

                    Intent intent = new Intent(context, EditarCorteActivity.class);

                    intent.putExtra("CORTE", corte);

                    ((AppCompatActivity) context).startActivityForResult(intent, 0);
                }
            }
        });
    }

    public ArrayList<Corte> getDados() {
        return dados;
    }

    public void setDados(ArrayList<Corte> dados) {
        this.dados = dados;
    }
}
