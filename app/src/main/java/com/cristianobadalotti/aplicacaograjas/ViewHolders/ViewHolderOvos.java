package com.cristianobadalotti.aplicacaograjas.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cristianobadalotti.aplicacaograjas.Entidades.Ovos;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarOvosActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class ViewHolderOvos extends RecyclerView.ViewHolder {

    public TextView textQuantidade;
    public TextView textQualidade;
    public TextView textData;
    public TextView textOPostura;
    public TextView textTipoAve;
    public TextView textCodigo;

    private ArrayList<Ovos> dados;

    public ViewHolderOvos(@NonNull View itemView, final Context context) {
        super(itemView);

        textQuantidade = itemView.findViewById(R.id.textViewQuantidadeOvos);
        textQualidade = itemView.findViewById(R.id.textViewQualidadeOvos);
        textData = itemView.findViewById(R.id.textViewDataOvos);
        textOPostura = itemView.findViewById(R.id.textViewOvosPostura);
        textTipoAve = itemView.findViewById(R.id.textViewTipoAveOvos);
        textCodigo = itemView.findViewById(R.id.textViewCodigoOvo);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getDados().size() > 0) {

                    Ovos ovos = getDados().get(getLayoutPosition());

                    Intent intent = new Intent(context, EditarOvosActivity.class);

                    intent.putExtra("OVOS", ovos);

                    ((AppCompatActivity) context).startActivityForResult(intent, 0);
                }
            }
        });
    }

    public ArrayList<Ovos> getDados() {
        return dados;
    }

    public void setDados(ArrayList<Ovos> dados) {
        this.dados = dados;
    }
}
