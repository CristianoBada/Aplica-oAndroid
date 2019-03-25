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

import com.cristianobadalotti.aplicacaograjas.Entidades.Corte;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarCorteActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class CorteAdapter extends RecyclerView.Adapter<CorteAdapter.ViewHolderCorte> {
    private ArrayList<Corte> dados;

    public CorteAdapter(ArrayList<Corte> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public CorteAdapter.ViewHolderCorte onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_corte, viewGroup, false);

        CorteAdapter.ViewHolderCorte holderCorte = new CorteAdapter.ViewHolderCorte(view, viewGroup.getContext());

        return holderCorte;
    }

    @Override
    public void onBindViewHolder(@NonNull CorteAdapter.ViewHolderCorte viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Corte corte = dados.get(i);
            viewHolder.textComentario.setText("Observção: " + corte.getComentario());
            viewHolder.textDataEntrada.setText("Data de Entrada: " + corte.getDataEntrada());
            viewHolder.textDataSaida.setText("Data de Saida: " + corte.getDataSaida());
            viewHolder.textMaxAves.setText("Maximo de aves: " + corte.getMaximo());
            viewHolder.textQuantidade.setText("Quantidade de aves: " + corte.getQuantidadeAves());
            viewHolder.textTipoAve.setText("Tipo de ave: " + corte.getTipoAve());
            viewHolder.textMortalidade.setText("Mortalidade: " + corte.getMortalidade());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderCorte extends RecyclerView.ViewHolder {

        public TextView textQuantidade;
        public TextView textComentario;
        public TextView textMaxAves;
        public TextView textDataEntrada;
        public TextView textDataSaida;
        public TextView textTipoAve;
        public TextView textMortalidade;

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

                    if (dados.size() > 0) {

                        Corte corte = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, EditarCorteActivity.class);

                        intent.putExtra("CORTE", corte);

                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });
        }
    }
}
