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

import java.util.ArrayList;

public class FinanceiroAdapter extends RecyclerView.Adapter<FinanceiroAdapter.ViewHolderFinanceiro> {
    private ArrayList<Financeiro> dados;

    public FinanceiroAdapter(ArrayList<Financeiro> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public FinanceiroAdapter.ViewHolderFinanceiro onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_financeiro, viewGroup, false);

        FinanceiroAdapter.ViewHolderFinanceiro holderFinanceiro = new FinanceiroAdapter.ViewHolderFinanceiro(view, viewGroup.getContext());

        return holderFinanceiro;
    }

    @Override
    public void onBindViewHolder(@NonNull FinanceiroAdapter.ViewHolderFinanceiro viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Financeiro financeiro = dados.get(i);
            viewHolder.textValor.setText("Valor: " + financeiro.getValor());
            viewHolder.textEntradaSaida.setText("Tipo de transição: " + financeiro.getEntrasaida());
            viewHolder.textDetalhe.setText("Observação: " + financeiro.getDetalhe());
            viewHolder.textNome.setText("Produto: " + financeiro.getNome());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderFinanceiro extends RecyclerView.ViewHolder {

        public TextView textNome;
        public TextView textValor;
        public TextView textEntradaSaida;
        public TextView textDetalhe;

        public ViewHolderFinanceiro(@NonNull View itemView, final Context context) {
            super(itemView);

            textNome = itemView.findViewById(R.id.textViewNomeFinanceiro);
            textDetalhe = itemView.findViewById(R.id.textViewDetalheFinanceiro);
            textEntradaSaida = itemView.findViewById(R.id.textViewEntradaFinanceiro);
            textValor = itemView.findViewById(R.id.textViewValorFinanceiro);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (dados.size() > 0) {

                        Financeiro financeiro = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, EditarFinanceiroActivity.class);

                        intent.putExtra("FINANCEIRO", financeiro);

                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });
        }
    }
}
