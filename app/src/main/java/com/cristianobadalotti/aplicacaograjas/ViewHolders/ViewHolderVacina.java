package com.cristianobadalotti.aplicacaograjas.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cristianobadalotti.aplicacaograjas.Entidades.Vacina;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarVacinasActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class ViewHolderVacina extends RecyclerView.ViewHolder {

    public TextView textTipo;
    public TextView textData;
    public TextView textDetalhe;
    private ArrayList<Vacina> dados;

    public ViewHolderVacina(@NonNull View itemView, final Context context) {
        super(itemView);

        textTipo = itemView.findViewById(R.id.textViewTipoVacina);
        textData = itemView.findViewById(R.id.textViewDataVacina);
        textDetalhe = itemView.findViewById(R.id.textViewDetalheVacina);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dados.size() > 0) {

                    Vacina vacina = dados.get(getLayoutPosition());

                    Intent intent = new Intent(context, EditarVacinasActivity.class);

                    intent.putExtra("VACINA", vacina);

                    ((AppCompatActivity) context).startActivityForResult(intent, 0);
                }
            }
        });
    }

    public ArrayList<Vacina> getDados() {
        return dados;
    }

    public void setDados(ArrayList<Vacina> dados) {
        this.dados = dados;
    }
}
