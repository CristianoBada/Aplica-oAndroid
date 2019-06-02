package com.cristianobadalotti.aplicacaograjas.ViewHolders;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.cristianobadalotti.aplicacaograjas.Entidades.Financeiro;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarFinanceiroActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class ViewHolderFinanceiro extends RecyclerView.ViewHolder {

    public TextView textNome;
    public TextView textValor;
    public TextView textEntradaSaida;
    public TextView textDetalhe;
    public TextView textCodigo;
    public TextView textData;
    private ArrayList<Financeiro> dados;

    public ViewHolderFinanceiro(@NonNull View itemView, final Context context) {
        super(itemView);

        textNome = itemView.findViewById(R.id.textViewNomeFinanceiro);
        textDetalhe = itemView.findViewById(R.id.textViewDetalheFinanceiro);
        textEntradaSaida = itemView.findViewById(R.id.textViewEntradaFinanceiro);
        textValor = itemView.findViewById(R.id.textViewValorFinanceiro);
        textCodigo = itemView.findViewById(R.id.textViewCodigoFinanceiro);
        textData = itemView.findViewById(R.id.textViewDataFinanceiro);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (getDados().size() > 0) {

                    Financeiro financeiro = getDados().get(getLayoutPosition());

                    Intent intent = new Intent(context, EditarFinanceiroActivity.class);

                    intent.putExtra("FINANCEIRO", financeiro);

                    ((AppCompatActivity) context).startActivityForResult(intent, 0);
                }
            }
        });
    }

    public ArrayList<Financeiro> getDados() {
        return dados;
    }

    public void setDados(ArrayList<Financeiro> dados) {
        this.dados = dados;
    }
}
