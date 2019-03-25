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

import com.cristianobadalotti.aplicacaograjas.Entidades.Racao;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarRacoesActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class RacaoAdapter extends RecyclerView.Adapter<RacaoAdapter.ViewHolderRacao> {
    private ArrayList<Racao> dados;

    public RacaoAdapter(ArrayList<Racao> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public RacaoAdapter.ViewHolderRacao onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_racao, viewGroup, false);

        RacaoAdapter.ViewHolderRacao holderRacao = new RacaoAdapter.ViewHolderRacao(view, viewGroup.getContext());

        return holderRacao;
    }

    @Override
    public void onBindViewHolder(@NonNull RacaoAdapter.ViewHolderRacao viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Racao racao = dados.get(i);
            viewHolder.textDataEntrada.setText("Data de Entrada: " + racao.getDataEntrada());
            viewHolder.textQuantidade.setText("Quantidade de aves: " + racao.getQuantidade());
            viewHolder.textTipoRacao.setText("Tipo de ração: " + racao.getTipoRacao());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderRacao extends RecyclerView.ViewHolder {

        public TextView textQuantidade;
        public TextView textTipoRacao;
        public TextView textDataEntrada;

        public ViewHolderRacao(@NonNull View itemView, final Context context) {
            super(itemView);

            textQuantidade = itemView.findViewById(R.id.textViewQuantidadeRacao);
            textDataEntrada = itemView.findViewById(R.id.textViewDataRacao);
            textTipoRacao = itemView.findViewById(R.id.textView324);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (dados.size() > 0) {

                        Racao racao = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, EditarRacoesActivity.class);

                        intent.putExtra("RACAO", racao);

                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });
        }
    }
}
