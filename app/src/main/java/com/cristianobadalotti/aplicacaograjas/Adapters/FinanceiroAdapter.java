package com.cristianobadalotti.aplicacaograjas.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cristianobadalotti.aplicacaograjas.Entidades.Financeiro;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarFinanceiroActivity;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.ViewHolders.ViewHolderFinanceiro;

import java.util.ArrayList;

public class FinanceiroAdapter extends RecyclerView.Adapter<ViewHolderFinanceiro> {
    private ArrayList<Financeiro> dados;

    public FinanceiroAdapter(ArrayList<Financeiro> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ViewHolderFinanceiro onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_financeiro, viewGroup, false);

        ViewHolderFinanceiro holderFinanceiro = new ViewHolderFinanceiro(view, viewGroup.getContext());
        holderFinanceiro.setDados(dados);

        return holderFinanceiro;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFinanceiro viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Financeiro financeiro = dados.get(i);
            viewHolder.textValor.setText("Valor: " + financeiro.getValor());
            viewHolder.textEntradaSaida.setText("Tipo de transição: " + financeiro.getEntrasaida());
            viewHolder.textDetalhe.setText("Observação: " + financeiro.getDetalhe());
            viewHolder.textNome.setText("Produto: " + financeiro.getNome());
            viewHolder.textData.setText("Data: " + financeiro.getData());
            viewHolder.textCodigo.setText("Código: " + financeiro.getCodigoFinanceiro());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}
