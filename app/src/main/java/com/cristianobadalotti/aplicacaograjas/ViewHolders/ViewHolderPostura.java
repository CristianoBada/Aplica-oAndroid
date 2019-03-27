package com.cristianobadalotti.aplicacaograjas.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cristianobadalotti.aplicacaograjas.Entidades.Postura;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarPosturaActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class ViewHolderPostura extends RecyclerView.ViewHolder{
    public TextView textQuantidade;
    public TextView textComentario;
    public TextView textMaxAves;
    public TextView textDataEntrada;
    public TextView textDataSaida;
    public TextView textTipoAve;
    private ArrayList<Postura> dados;

    public ViewHolderPostura(@NonNull View itemView, final Context context) {
        super(itemView);

        textQuantidade = itemView.findViewById(R.id.textViewQuantidade);
        textComentario = itemView.findViewById(R.id.textViewComentario);
        textMaxAves = itemView.findViewById(R.id.textViewMaxAves);
        textDataEntrada = itemView.findViewById(R.id.textViewDataEntrada);
        textDataSaida = itemView.findViewById(R.id.textViewDataSaida);
        textTipoAve = itemView.findViewById(R.id.textViewTipoAve);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getDados().size() > 0) {

                    Postura postura = getDados().get(getLayoutPosition());

                    Intent intent = new Intent(context, EditarPosturaActivity.class);

                    intent.putExtra("POSTURA", postura);

                    ((AppCompatActivity) context).startActivityForResult(intent, 0);
                }
            }
        });
    }


    public ArrayList<Postura> getDados() {
        return dados;
    }

    public void setDados(ArrayList<Postura> dados) {
        this.dados = dados;
    }
}
