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

import com.cristianobadalotti.aplicacaograjas.Entidades.Postura;
import com.cristianobadalotti.aplicacaograjas.Forms.EditarPosturaActivity;
import com.cristianobadalotti.aplicacaograjas.R;

import java.util.ArrayList;

public class PosturaAdapter extends RecyclerView.Adapter<PosturaAdapter.ViewHolderPostura> {
    private ArrayList<Postura> dados;

    public PosturaAdapter(ArrayList<Postura> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public PosturaAdapter.ViewHolderPostura onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_postura, viewGroup, false);

        PosturaAdapter.ViewHolderPostura holderPostura = new PosturaAdapter.ViewHolderPostura(view, viewGroup.getContext());

        return holderPostura;
    }

    @Override
    public void onBindViewHolder(@NonNull PosturaAdapter.ViewHolderPostura viewHolder, int i) {

        if ( (dados != null) && (dados.size() > 0)) {
            Postura postura = dados.get(i);
            viewHolder.textComentario.setText("Observção: " + postura.getComentario());
            viewHolder.textDataEntrada.setText("Data de Entrada: " + postura.getDataentrada());
            viewHolder.textDataSaida.setText("Data de Saida: " + postura.getDatasaida());
            viewHolder.textMaxAves.setText("Maximo de aves: " + postura.getMaximoAves());
            viewHolder.textQuantidade.setText("Quantidade de aves: " + postura.getQuantidade());
            viewHolder.textTipoAve.setText("Tipo de ave: " + postura.getTipoAve());
        }
    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    public class ViewHolderPostura extends RecyclerView.ViewHolder {

        public TextView textQuantidade;
        public TextView textComentario;
        public TextView textMaxAves;
        public TextView textDataEntrada;
        public TextView textDataSaida;
        public TextView textTipoAve;

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

                    if (dados.size() > 0) {

                        Postura postura = dados.get(getLayoutPosition());

                        Intent intent = new Intent(context, EditarPosturaActivity.class);

                        intent.putExtra("POSTURA", postura);

                        ((AppCompatActivity) context).startActivityForResult(intent, 0);
                    }
                }
            });
        }
    }
}
