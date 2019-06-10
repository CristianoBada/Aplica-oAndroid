package com.cristianobadalotti.aplicacaograjas.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cristianobadalotti.aplicacaograjas.Entidades.Racao;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarRacoesActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class ViewHolderRacao extends RecyclerView.ViewHolder {

    public TextView textQuantidade;
    public TextView textTipoRacao;
    public TextView textDataEntrada;
    public TextView textCodigo;
    public TextView textCodigoCorte;
    public TextView textCodigoPostura;
    private ArrayList<Racao> dados;

    public ViewHolderRacao(@NonNull View itemView, final Context context) {
        super(itemView);

        textQuantidade = itemView.findViewById(R.id.textViewQuantidadeRacao);
        textDataEntrada = itemView.findViewById(R.id.textViewDataRacao);
        textTipoRacao = itemView.findViewById(R.id.textView324);
        textCodigo = itemView.findViewById(R.id.textViewCodigoRacao);
        textCodigoCorte = itemView.findViewById(R.id.textViewRacaoCorte);
        textCodigoPostura = itemView.findViewById(R.id.textViewRacaoPostura);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getDados().size() > 0) {

                    Racao racao = getDados().get(getLayoutPosition());

                    Intent intent = new Intent(context, EditarRacoesActivity.class);

                    intent.putExtra("RACAO", racao);

                    ((AppCompatActivity) context).startActivityForResult(intent, 0);
                }
            }
        });
    }

    public ArrayList<Racao> getDados() {
        return dados;
    }

    public void setDados(ArrayList<Racao> dados) {
        this.dados = dados;
    }
}
