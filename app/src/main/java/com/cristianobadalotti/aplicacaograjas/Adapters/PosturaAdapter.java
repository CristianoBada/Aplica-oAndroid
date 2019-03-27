package com.cristianobadalotti.aplicacaograjas.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cristianobadalotti.aplicacaograjas.Entidades.Postura;
import com.cristianobadalotti.aplicacaograjas.R;
import com.cristianobadalotti.aplicacaograjas.ViewHolder.ViewHolderPostura;

import java.util.ArrayList;

public class PosturaAdapter extends RecyclerView.Adapter<ViewHolderPostura> {
    private ArrayList<Postura> dados;

    public PosturaAdapter(ArrayList<Postura> dados) {
        this.dados = dados;
    }

    @NonNull
    @Override
    public ViewHolderPostura onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(viewGroup.getContext());

        View view = layoutInflater.inflate(R.layout.item_postura, viewGroup, false);

        ViewHolderPostura holderPostura = new ViewHolderPostura(view, viewGroup.getContext());
        holderPostura.setDados(dados);

        return holderPostura;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderPostura viewHolder, int i) {

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
}
